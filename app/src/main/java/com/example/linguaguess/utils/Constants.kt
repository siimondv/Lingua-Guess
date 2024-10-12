package com.example.linguaguess.utils

object Constants {

    const val TIMEOUT: Long = 15
    const val ERROR = "Error"

    //Collection UI Detail Error Messages
    const val COLLECTION_DETAIL_ERROR_MSG_COMMON =
        "There has been an error retrieving the collection. Please try again later."
    const val COLLECTION_DOWNLOADED_SUCCESS_MSG = "Collection downloaded successfully"
    const val COLLECTION_DOWNLOAD_ERROR_MSG =
        "There has been an error downloading the collection. Please try again later."

    //Registration UI Error Messages
    const val REGISTRATION_ERROR_MSG_EMPTY_NAME = "Please enter your user name"
    const val REGISTRATION_ERROR_MSG_EMPTY_EMAIL = "Please enter your email"
    const val REGISTRATION_ERROR_MSG_EMPTY_MOBILE = "Please enter your mobile number"
    const val REGISTRATION_ERROR_MSG_EMPTY_PASSWORD = "Please enter your password"
    const val REGISTRATION_ERROR_MSG_EMPTY_CONFIRM_PASSWORD = "Please confirm your password"
    const val REGISTRATION_ERROR_MSG_PASSWORD_MISMATCH =
        "Please enter the same password here as above"
    const val REGISTRATION_ERROR_MSG_FAILED = "Registration failed. Please try again."

    //Login UI Error Messages
    const val LOGIN_ERROR_MSG_EMPTY_EMAIL = "Please enter your email"
    const val LOGIN_ERROR_MSG_EMPTY_PASSWORD = "Please enter your password"
    const val LOGIN_ERROR_MSG_FAILED = "Login failed. Please try again."

    //Quiz UI Error Messages
    const val QUIZ_WORDS_NOT_LOADED_ERROR_MSG =
        "There has been an error loading the words. Please try again later."
    const val QUIZ_FINISHED_FAIL_ERROR_MSG =
        "There has been an error finishing the quiz. Please try again later."


    //Library UI Error Messages
    const val LIBRARY_COLLECTIONS_NOT_LOADED_ERROR_MSG =
        "There has been an error loading the collections. Please try again later."

    //Download UI Error Messages
    const val DOWNLOAD_COLLECTIONS_NOT_LOADED_ERROR_MSG =
        "There has been an error loading the collections. Please try again later."

    //Chapters UI Error Messages
    const val CHAPTERS_NOT_LOADED_ERROR_MSG =
        "There has been an error loading the chapters. Please try again later."

    //Blocks UI Error Messages
    const val BLOCKS_NOT_LOADED_ERROR_MSG =
        "There has been an error loading the blocks. Please try again later."

    //Local Score Data Source Error Messages
    const val SCORE_NOT_RETRIEVED = "The score could not be retrieved"
    const val SCORE_NOT_SAVED = "The score could not be saved"
    const val SCORE_NOT_UPDATED = "The score could not be updated"

    //Local Chapters Data Source Error Messages
    const val CHAPTER_NOT_RETRIEVED = "The chapters could not be retrieved"
    const val TOTAL_WORDS_NOT_RETRIEVED = "The total words could not be retrieved"

    //Local Collection Data Source Error Messages
    const val COLLECTIONS_NOT_RETRIEVED = "The collections could not be retrieved"

    //Local Collection With Chapters And Words Data Source Error Messages
    const val COLLECTION_NOT_SAVED = "The collection could not be saved"

    //Local Japanese Word Data Source Error Messages
    const val JAPANESE_WORDS_NOT_RETRIEVED = "The japanese words could not be retrieved"

    //Remote Chapter Data Source Error Messages
    const val NO_CHAPTERS_FOUND = "No chapters found"

    //Remote Collection Data Source Error Messages
    const val COLLECTIONS_NOT_FOUND = "Collections not found"
    const val COLLECTION_NOT_FOUND = "Collection not found"

    //Remote Japanese Word Data Source Error Messages
    const val NO_JAPANESE_WORDS_FOUND = "No Japanese words found"

    //Remote Auth Data Source Error Messages
    const val NO_USER_FOUND = "No user found"

    //Local Database Constants
    const val LOCAL_DATABASE_NAME = "collections.db"

    // Save Local Collection With Chapter And Words Use Case
    const val ERROR_GET_COLLECTION_DETAILS = "Failed to load collection details"
    const val NO_CHAPTERS_IN_COLLECTION = "No chapters found for this collection"
    const val NO_WORDS_CHAPTERS = "Some chapters have no words"
    const val ERROR_SAVING_DATA = "Failed to save data locally"

    //Data Store Constants
    const val DATA_STORE = "data_store"

    //Token Constants
    const val ACCESSTOKEN = "access_token"
    const val REFRESHTOKEN = "refresh_token"

}