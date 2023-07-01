package com.techfortyone.propertyfinders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techfortyone.propertyfinders.data.MainRepository
import com.techfortyone.propertyfinders.data.model.FacilityMainRealm
import com.techfortyone.propertyfinders.data.model.PropertyData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    init {
        getData()
    }
    private val _propertyData = MutableLiveData<PropertyData>()

    val propertyData : LiveData<PropertyData>
        get() = _propertyData

    private fun getData() = viewModelScope.launch {
        val data = repository.getPropertyData()
        _propertyData.postValue(data)
    }

}