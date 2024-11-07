package ru.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewModel: ViewModel() {

    val repo = ListRepoitory()
    val mutableLiveDataList = MutableLiveData<ArrayList<Film>>()
    val mutableLiveDataTextList = MutableLiveData<String>()
    val mutableLiveDataKeyWord = MutableLiveData<String>()

    fun search(){
        val keyword = mutableLiveDataKeyWord.value
        mutableLiveDataList.value = keyword?.let { repo.getList().filterListYears(it) }
        mutableLiveDataTextList.value = mutableLiveDataList.value.toString()
    }
    fun ArrayList<Film>.filterListTitle( keyword:String):ArrayList<Film>{
        return this.filter { it -> it.title.lowercase().startsWith(keyword.lowercase())} as ArrayList<Film>


    }
    fun ArrayList<Film>.filterListYears( keyword:String):ArrayList<Film>{
        return this.filter{it ->
            val char = keyword[0]
            if(char=='>'){
                return@filter (it.year > (keyword.split(">")[1].toInt()))
            }
            else{
                return@filter (it.year < (keyword.split("<")[1].toInt()))
            }
        }  as ArrayList<Film>
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
}