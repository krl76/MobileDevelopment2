package ru.mirea.kornilov.sway.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.repository.RecognitionRepository
import ru.mirea.kornilov.sway.domain.repository.PlacesError
import ru.mirea.kornilov.sway.domain.repository.PlacesLoadResult
import ru.mirea.kornilov.sway.domain.models.User
import ru.mirea.kornilov.sway.domain.usecases.GetPlacesUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetProfileUseCase
import ru.mirea.kornilov.sway.domain.usecases.RecognizePlaceUseCase
import ru.mirea.kornilov.sway.domain.models.AuthFailure
import ru.mirea.kornilov.sway.domain.usecases.LogoutUseCase
import ru.mirea.kornilov.sway.domain.usecases.RefreshProfileUseCase
import ru.mirea.kornilov.sway.domain.usecases.UpdateProfileUseCase

class MainViewModel(
    private val placeRepository: PlaceRepository,
    private val authRepository: AuthRepository,
    private val recognitionRepository: RecognitionRepository
) : ViewModel() {

    private val places = MutableLiveData<List<Place>>(emptyList())
    private val avatarLetter = MutableLiveData("?")
    private val login = MutableLiveData("")
    private val nickname = MutableLiveData("")
    private val firstName = MutableLiveData("")
    private val lastName = MutableLiveData("")
    private val displayName = MutableLiveData("")
    private val authorized = MutableLiveData(false)
    private val loggedOut = MutableLiveData(false)
    private val status = MutableLiveData(CatalogStatus.LOADING)

    init {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel created")
        loadPlaces()
        showUser(GetProfileUseCase(authRepository).execute())
        RefreshProfileUseCase(authRepository).execute { user -> showUser(user) }
    }

    private fun showUser(user: User?) {
        val userLogin = user?.login
        login.value = userLogin.orEmpty()
        nickname.value = user?.nickname.orEmpty()
        firstName.value = user?.firstName.orEmpty()
        lastName.value = user?.lastName.orEmpty()
        displayName.value = listOf(user?.firstName, user?.lastName)
            .map { it?.trim().orEmpty() }
            .filter { it.isNotEmpty() }
            .joinToString(" ")
        val letterSource = user?.nickname?.trim()?.takeIf { it.isNotEmpty() }
            ?: user?.firstName?.trim()?.takeIf { it.isNotEmpty() }
            ?: userLogin
        avatarLetter.value = letterSource?.firstOrNull()?.uppercaseChar()?.toString() ?: "?"
        authorized.value = user != null
    }

    fun getPlaces(): LiveData<List<Place>> = places
    fun getAvatarLetter(): LiveData<String> = avatarLetter
    fun getLogin(): LiveData<String> = login
    fun getNickname(): LiveData<String> = nickname
    fun getFirstName(): LiveData<String> = firstName
    fun getLastName(): LiveData<String> = lastName
    fun getDisplayName(): LiveData<String> = displayName

    fun updateProfile(
        nickname: String,
        firstName: String,
        lastName: String,
        onResult: (AuthFailure?) -> Unit
    ) {
        UpdateProfileUseCase(authRepository).execute(nickname, firstName, lastName) { failure ->
            if (failure == null) {
                showUser(GetProfileUseCase(authRepository).execute())
            }
            onResult(failure)
        }
    }
    fun getAuthorized(): LiveData<Boolean> = authorized
    fun getLoggedOut(): LiveData<Boolean> = loggedOut
    fun getStatus(): LiveData<CatalogStatus> = status

    fun logout() {
        LogoutUseCase(authRepository).execute()
        loggedOut.value = true
    }

    fun recognize(): Pair<SceneType, List<Place>> {
        return RecognizePlaceUseCase(recognitionRepository, placeRepository).execute()
    }

    fun loadPlaces() {
        status.value = CatalogStatus.LOADING
        GetPlacesUseCase(placeRepository).execute { result ->
            when (result) {
                is PlacesLoadResult.Success -> {
                    places.value = result.places
                    status.value = CatalogStatus.READY
                }
                is PlacesLoadResult.Failure -> {
                    places.value = emptyList()
                    status.value = when (result.error) {
                        PlacesError.NO_NETWORK -> CatalogStatus.NO_NETWORK
                        PlacesError.BAD_RESPONSE -> CatalogStatus.BAD_RESPONSE
                    }
                }
            }
        }
    }

    enum class CatalogStatus {
        LOADING,
        READY,
        NO_NETWORK,
        BAD_RESPONSE
    }

    override fun onCleared() {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel cleared")
        super.onCleared()
    }
}
