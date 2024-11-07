package ru.example.myapplication

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewModel: ViewModel() {

    val repo = ListRepoitory()
    val mutableLiveDataList = MutableLiveData<ArrayList<Film>>()
    val mutableLiveDataTextList = MutableLiveData<String>().apply {
        value = repo.getList().toString()
    }
    val mutableLiveDataKeyWord = MutableLiveData<String>()
    var filterMode = "Years"
    fun search(){
        if(filterMode.equals("Years")) {
            val keyword = mutableLiveDataKeyWord.value
            mutableLiveDataList.value = keyword?.let { repo.getList().filterListYears(it) }
            mutableLiveDataTextList.value = mutableLiveDataList.value.toString()
        }
        else {
            val keyword = mutableLiveDataKeyWord.value
            mutableLiveDataList.value = keyword?.let { repo.getList().filterListTitle(it) }
            mutableLiveDataTextList.value = mutableLiveDataList.value.toString()
        }

    }
    fun ArrayList<Film>.filterListTitle( keyword:String):ArrayList<Film>{
        return this.filter { it -> it.title.lowercase().startsWith(keyword.lowercase())} as ArrayList<Film>


    }
    fun ArrayList<Film>.filterListYears( keyword:String): ArrayList<Film> {
        return this.filter { it ->
            if (keyword.length > 0) {
                val char = keyword[0]
                if (char == '>') {
                    if (keyword.split(">")[1].length != 0) {
                        return@filter (it.year > (keyword.split(">")[1].toInt()))
                    } else {
                        return ArrayList<Film>()
                    }
                } else {
                    if (keyword.split("<")[1].length != 0) {
                        return@filter (it.year < (keyword.split("<")[1].toInt()))
                    } else {
                        return ArrayList<Film>()
                    }
                }

            }
            else{
                return ArrayList<Film>()
            }
        }as ArrayList<Film>
    }
    fun ArrayList<Film>.sortListDirector( keyword:String):ArrayList<Film>{
        //> <  лексико-графически сортировать

        if(keyword.equals(">")){
            return this.sortedBy { it -> it.director }.toList() as ArrayList<Film>
        }
        else{
            return this.sortedByDescending{it -> it.director}.toList() as ArrayList<Film>
        }

    }
    fun ChangeFilter() = if(filterMode.equals("Years")){
        filterMode = "Titles"


    }
    else{
        filterMode = "Years"
    }
}