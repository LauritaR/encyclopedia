package com.example.encyclopedia.data.remote

import com.example.encyclopedia.presentation.viewmodel.CategoryMembersResponse
import com.example.encyclopedia.presentation.viewmodel.PageDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WikipediaApi {
    @GET("w/api.php")
     fun getCategoryMembers(
        @Query("action") action: String = "query",
        @Query("list") list: String = "categorymembers",
        @Query("cmtitle") categoryTitle: String,
        @Query("format") format: String = "json",
        @Query("cmlimit") limit: Int = 50
    ): Call<CategoryMembersResponse>

    @GET("w/api.php")
     fun getPageDetails(
        @Query("action") action: String = "query",
        @Query("prop") prop: String = "pageimages|extracts",
        @Query("format") format: String = "json",
        @Query("pageid") pageId: Int? = 0,
        @Query("titles") title: String? = null,
        @Query("exintro") exintro: Boolean = true,
        @Query("explaintext") explaintext: Boolean = true,
        @Query("pithumbsize") thumbnailSize: Int = 500
    ): Call<PageDetailsResponse>
}