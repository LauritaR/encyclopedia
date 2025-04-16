package com.example.encyclopedia.presentation.viewmodel

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.encyclopedia.data.remote.RetrofitInstance
import com.example.encyclopedia.presentation.components.CategoryMembersScreen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class CategoryMembersResponse(
    val query: CategoryQuery
)

data class CategoryQuery(
    val categorymembers: List<CategoryMember>
)

data class CategoryMember(
    val pageid: Int,
    val title: String,
    val description:String? = null,
    var imageUrl:String? =null,
    var links: List<String>? = null
)

data class PageDetailsResponse(
    val query: PageDetailsQuery
)

data class PageDetailsQuery(
    val pages: Map<String, PageDetails>
)

data class PageDetails(
    val pageid: Int,
    val title: String,
    val thumbnail: Thumbnail?,
    val description: String?,
    val extract:String?,
    val links: List<Link>?
)
data class Link(
    val title: String
)
data class Thumbnail(
    val source: String
)

class ApiViewModel: ViewModel(){
    val categoryMembers = mutableStateListOf<CategoryMember>()
    private val _currentIndex= mutableStateOf(0)
    val currentIndex: State<Int> = _currentIndex
    val isLoading= mutableStateOf(false)

    fun fetchCategoryMembers(categoryTitle:String) {
        isLoading.value=true
        fetchCategoryMembersList(categoryTitle) { members ->
            categoryMembers.clear()
            val filteredMembers= members.filter { it.imageUrl != null && it.imageUrl!!.isNotEmpty() }
            categoryMembers.addAll(filteredMembers)
            categoryMembers.forEachIndexed {index, categoryMember ->
                fetchCategoryMemberDetails(categoryMember.pageid) { updatedMember ->
                    categoryMembers[index] = updatedMember
                }
            }
            isLoading.value=false
        }
    }

    fun fetchCategoryMembersList(categoryTitle: String, onFetched:(List<CategoryMember>)->Unit) {
        RetrofitInstance.api.getCategoryMembers(categoryTitle = categoryTitle)
            .enqueue(object : Callback<CategoryMembersResponse> {
                override fun onResponse(
                    call: Call<CategoryMembersResponse>,
                    response: Response<CategoryMembersResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.query?.categorymembers?.let { members ->
                            val updatedMembers = mutableListOf<CategoryMember>()
                            members.forEach { categoryMember ->
                                fetchCategoryMemberDetailsByTitle(categoryMember.title) { updatedMember ->
                                    updatedMembers.add(updatedMember)
                                    if (updatedMembers.size == members.size) {
                                        onFetched(updatedMembers)
                                    }
                                }
                            }
                        }
                    } else {
                        Log.e("CategoryMembers", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CategoryMembersResponse>, t: Throwable) {
                    Log.e("CategoryMembers", "Failure: ${t.message}")
                }
            })
    }
    fun fetchCategoryMemberDetails(pageid:Int, onFetched: (CategoryMember) -> Unit) {
        RetrofitInstance.api.getPageDetails(pageId = pageid)
            .enqueue(object : Callback<PageDetailsResponse> {
                override fun onResponse(
                    call: Call<PageDetailsResponse>,
                    response: Response<PageDetailsResponse>
                ) {
                    if (response.isSuccessful) {

                        val pageDetails = response.body()?.query?.pages?.values?.firstOrNull()
                        pageDetails?.let{
                            val updatedMember = CategoryMember(
                                pageid = it.pageid,
                                title = it.title,
                                description = it.description,
                                imageUrl = it.thumbnail?.source,
                                links = it.links?.map { link -> link.title }
                            )
                            onFetched(updatedMember)
                        }
                    } else {
                        Log.e("CategoryMembers", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<PageDetailsResponse>, t: Throwable) {
                    Log.e("CategoryMembers", "Error: ${t.message}")
                }
            })
    }
    fun fetchCategoryMemberDetailsByTitle(title: String, onFetched: (CategoryMember) -> Unit) {
        RetrofitInstance.api.getPageDetails(title = title)
            .enqueue(object : Callback<PageDetailsResponse> {
                override fun onResponse(
                    call: Call<PageDetailsResponse>,
                    response: Response<PageDetailsResponse>
                ) {
                    if (response.isSuccessful) {
                        val pageDetails = response.body()?.query?.pages?.values?.firstOrNull()

                        pageDetails?.let{
                            val updatedCategoryMember = CategoryMember(
                                pageid = pageDetails.pageid,
                                title = pageDetails.title,
                                description = pageDetails.extract,
                                imageUrl = pageDetails.thumbnail?.source
                            )
                            onFetched(updatedCategoryMember)
                        }
                    } else {
                        Log.e("CategoryMemberDetails", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<PageDetailsResponse>, t: Throwable) {
                    Log.e("CategoryMemberDetails", "Error: ${t.message}")
                }
            })
    }
    fun onClickedPrevious() {
        if(_currentIndex.value>0){
            _currentIndex.value -= 1
        }
    }
    fun onClickedNext(){
        if(_currentIndex.value<categoryMembers.size -1 ){
            _currentIndex.value += 1
        }
    }

    fun setCurrentIndex(index:Int){
        _currentIndex.value=index
    }

}
