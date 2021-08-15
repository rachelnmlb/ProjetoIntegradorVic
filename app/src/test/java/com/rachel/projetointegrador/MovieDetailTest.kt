package com.rachel.projetointegrador

import com.rachel.projetointegrador.data.model.MovieDetail
import com.rachel.projetointegrador.data.model.CertificationResultsList
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MovieDetailTest {

    @Test
    fun runtime_string_with_null_duration_returns_0h() {
        val runtime = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            certifications = CertificationResultsList(mutableListOf())
        ).runtimeString()

        assertEquals("0h", runtime)
    }

    @Test
    fun runtime_string_with_zero_duration_returns_0h() {

        val runtime = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            runtime = 0,
            certifications = CertificationResultsList(mutableListOf())
        ).runtimeString()

        assertEquals("0h", runtime)
    }

    @Test
    fun runtime_string_with_whole_hour_duration_returns_only_hour_part() {

        val runtime1 = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            runtime = 120,
            certifications = CertificationResultsList(mutableListOf())
        ).runtimeString()

        val runtime2 = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            runtime = 60,
            certifications = CertificationResultsList(mutableListOf())
        ).runtimeString()

        assertEquals("2h", runtime1)
        assertEquals("1h", runtime2)
    }

    @Test
    fun runtime_string_with_less_than_one_hour_duration_returns_only_minutes_part() {

        val runtime = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            runtime = 30,
            certifications = CertificationResultsList(mutableListOf())
        ).runtimeString()

        assertEquals("30min", runtime)
    }

    @Test
    fun runtime_string_with_fraction_hour_duration_returns_correct_result() {

        val runtime1 = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            runtime = 125, // leading zero minutes
            certifications = CertificationResultsList(mutableListOf())
        ).runtimeString()

        val runtime2 = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            runtime = 145,
            certifications = CertificationResultsList(mutableListOf())
        ).runtimeString()

        assertEquals("2h 05min", runtime1)
        assertEquals("2h 25min", runtime2)
    }

    @Test
    fun release_year_with_invalid_date_string_returns_empty() {
        val nullDate = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            certifications = CertificationResultsList(mutableListOf())
        ).releaseYear()

        val emptyDate = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            releaseDate = "",
            certifications = CertificationResultsList(mutableListOf())
        ).releaseYear()

        val shortDate = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            releaseDate = "199",
            certifications = CertificationResultsList(mutableListOf())
        ).releaseYear()

        val nonDigitDate = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            releaseDate = "abcd-ef-gh",
            certifications = CertificationResultsList(mutableListOf())
        ).releaseYear()

        assertEquals("", nullDate)
        assertEquals("", emptyDate)
        assertEquals("", shortDate)
        assertEquals("", nonDigitDate)
    }

    @Test
    fun release_year_with_valid_date_string_returns_year() {

        val releaseYear = MovieDetail(
            id = 0,
            voteAverage = 100F,
            genres = listOf(),
            releaseDate = "1999-09-09",
            certifications = CertificationResultsList(mutableListOf())
        ).releaseYear()

        assertEquals("1999", releaseYear)
    }
}