package com.example.linguaguess.ui.screens.authenticated.quiz

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.linguaguess.domain.JapaneseWord
import com.example.linguaguess.ui.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizState())
    val uiState = _uiState.asStateFlow()


    private val words = listOf(
        JapaneseWord(
            translatedWord = "thank you for the meal (before eating)",
            hiraganaKatakana = "いただきます",
            kanji = "頂きます",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "thank you for the meal (after eating)",
            hiraganaKatakana = "ごちそうさまでした",
            kanji = "御馳走様でした",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "I am going and coming back",
            hiraganaKatakana = "いってきます",
            kanji = "行って来ます",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "please go and come back",
            hiraganaKatakana = "いってらっしゃい",
            kanji = "行ってらっしゃい",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "I am home",
            hiraganaKatakana = "ただいま",
            kanji = "只今",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "welcome home",
            hiraganaKatakana = "おかえり",
            kanji = "お帰り",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "how do you do?",
            hiraganaKatakana = "はじめまして",
            kanji = "始めまして",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "nice to meet you",
            hiraganaKatakana = "よろしくおねがいします",
            kanji = "宜しくお願いします",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "good morning",
            hiraganaKatakana = "おはよう",
            kanji = "お早う",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "good morning (polite)",
            hiraganaKatakana = "おはようございます",
            kanji = "お早うございます",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "good afternoon",
            hiraganaKatakana = "こんにちは",
            kanji = "今日は",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "good evening",
            hiraganaKatakana = "こんばんは",
            kanji = "今晩は",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "good night",
            hiraganaKatakana = "おやすみなさい",
            kanji = "お休みなさい",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "goodbye",
            hiraganaKatakana = "さようなら",
            kanji = "左様なら",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "thank you",
            hiraganaKatakana = "ありがとう",
            kanji = "有難う",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "excuse me",
            hiraganaKatakana = "すみません",
            kanji = "済みません",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "No",
            hiraganaKatakana = "いいえ",
            kanji = "いいえ",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "0",
            hiraganaKatakana = "ゼロ",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "1",
            hiraganaKatakana = "いち",
            kanji = "一",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "2",
            hiraganaKatakana = "に",
            kanji = "二",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "3",
            hiraganaKatakana = "さん",
            kanji = "三",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "4",
            hiraganaKatakana = "よん",
            kanji = "四",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "5",
            hiraganaKatakana = "ご",
            kanji = "五",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "6",
            hiraganaKatakana = "ろく",
            kanji = "六",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "7",
            hiraganaKatakana = "なな",
            kanji = "七",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "8",
            hiraganaKatakana = "はち",
            kanji = "八",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "9",
            hiraganaKatakana = "きゅう",
            kanji = "九",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "10",
            hiraganaKatakana = "じゅう",
            kanji = "十",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "11",
            hiraganaKatakana = "じゅういち",
            kanji = "十一",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "12",
            hiraganaKatakana = "じゅうに",
            kanji = "十二",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "13",
            hiraganaKatakana = "じゅうさん",
            kanji = "十三",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "14",
            hiraganaKatakana = "じゅうよん",
            kanji = "十四",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "15",
            hiraganaKatakana = "じゅうご",
            kanji = "十五",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "16",
            hiraganaKatakana = "じゅうろく",
            kanji = "十六",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "17",
            hiraganaKatakana = "じゅうなな",
            kanji = "十七",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "18",
            hiraganaKatakana = "じゅうはち",
            kanji = "十八",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "19",
            hiraganaKatakana = "じゅうきゅう",
            kanji = "十九",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "20",
            hiraganaKatakana = "にじゅう",
            kanji = "二十",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "30",
            hiraganaKatakana = "さんじゅう",
            kanji = "三十",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "40",
            hiraganaKatakana = "よんじゅう",
            kanji = "四十",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "50",
            hiraganaKatakana = "ごじゅう",
            kanji = "五十",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "60",
            hiraganaKatakana = "ろくじゅう",
            kanji = "六十",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "70",
            hiraganaKatakana = "ななじゅう",
            kanji = "七十",
            chapter = 0
        ),
        JapaneseWord(
            translatedWord = "80",
            hiraganaKatakana = "はちじゅう",
            kanji = "八十",
            chapter = 0
        )

    )

    private var correctWords = 0

    init {
        _uiState.update {
            it.copy(
                totalWordCount = words.size,
                currentWord = words[0]
            )
        }
    }

    fun setCheckAnswerEnabled() {
        _uiState.update {
            it.copy(isCheckAnswerEnabled = true)
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun getResourceIdForImage(context: Context, imageName: String): Int {
        //put the rsource id in state in viewmodel

        // Resolve the resource ID dynamically using resources.getIdentifier
        val packageName = context.packageName
        return try {
            context.resources.getIdentifier(imageName, "drawable", packageName)
        } catch (e: Exception) {
            e.printStackTrace()
            0 // Return 0 or a default resource ID if not found
        }
    }


    fun reset() {

        _uiState.update {
            it.copy(
                isCheckAnswerEnabled = false,
                gotItStatus = GotItStatus.NOT_PRESSED
            )
        }
        if (_uiState.value.gotItStatus == GotItStatus.GOT_IT_RIGHT) {
            correctWords--

        }
    }


    fun nextWord() {
        _uiState.update {
            it.copy(
                currentWordIndex = it.currentWordIndex + 1,
                currentWord = words[it.currentWordIndex + 1],
                totalProgress = ((it.currentWordIndex + 1) * 100) / it.totalWordCount,
                isCheckAnswerEnabled = false,
                gotItStatus = GotItStatus.NOT_PRESSED
            )
        }
    }

    fun goodAnswer() {
        _uiState.update {
            it.copy(
                gotItStatus = GotItStatus.GOT_IT_RIGHT

            )
        }
        correctWords++
    }

    fun badAnswer() {
        _uiState.update {
            it.copy(
                gotItStatus = GotItStatus.GOT_IT_WRONG
            )
        }
        if (_uiState.value.gotItStatus == GotItStatus.GOT_IT_RIGHT) {
            correctWords--

        }
    }


}


@Immutable
data class QuizState(
    val currentWord: JapaneseWord = JapaneseWord(),
    val totalWordCount: Int = 0,
    val currentWordIndex: Int = 0,
    val totalProgress: Int = 0,
    val isCheckAnswerEnabled: Boolean = false,
    val gotItStatus: GotItStatus = GotItStatus.NOT_PRESSED,
    val errorState: QuizErrorState = QuizErrorState(),
)

@Immutable
data class QuizErrorState(
    val wrongAnswerErrorState: ErrorState = ErrorState(),
)

enum class GotItStatus {
    NOT_PRESSED,
    GOT_IT_RIGHT,
    GOT_IT_WRONG
}


