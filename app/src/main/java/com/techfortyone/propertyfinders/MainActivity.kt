package com.techfortyone.propertyfinders

import android.graphics.Path.Op
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.AccessNetworkConstants.GeranBand
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.techfortyone.propertyfinders.data.local.ListItem
import com.techfortyone.propertyfinders.data.local.itemSort.GeneralItems
import com.techfortyone.propertyfinders.data.local.itemSort.HeaderId
import com.techfortyone.propertyfinders.data.model.Exclusion
import com.techfortyone.propertyfinders.data.model.Facility
import com.techfortyone.propertyfinders.data.model.FacilityAndOptions
import com.techfortyone.propertyfinders.data.model.Option
import com.techfortyone.propertyfinders.ui.MyPropertyRecyclerView
import com.techfortyone.propertyfinders.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MyPropertyRecyclerView
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.rv)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val exclusions: List<List<Exclusion>> = listOf(
            listOf(
                Exclusion("1", "4"),
                Exclusion("2", "6")
            ),
            listOf(
                Exclusion("1", "3"),
                Exclusion("3", "12")
            ),
            listOf(
                Exclusion("2", "7"),
                Exclusion("3", "12")
            )
        )
        val firstExclusion: Exclusion = exclusions[0][0]
        val firstNextExclusion: Exclusion = exclusions[0][1]
        val firstOptionsId: String = firstExclusion.options_id
        val firstFaciltyId: String = firstExclusion.facility_id
        val firstNextOptionsId: String = firstNextExclusion.options_id
        val firstNextFaciltyId: String = firstNextExclusion.facility_id


        val secondExclusion: Exclusion = exclusions[1][0]
        val secondNextExclusion: Exclusion = exclusions[1][1]
        val secondOptionsId: String = secondExclusion.options_id
        val secondNextOptionsId: String = secondNextExclusion.options_id
        val secondFaciltyId: String = secondExclusion.facility_id
        val secondNextFaciltyId: String = secondNextExclusion.facility_id

        val thirdExclusion: Exclusion = exclusions[2][0]
        val thirdNextExclusion: Exclusion = exclusions[2][1]
        val thirdOptionsId: String = thirdExclusion.options_id
        val thirdNextOptionsId: String = thirdNextExclusion.options_id
        val thirdFaciltyId: String = thirdExclusion.facility_id
        val thirdNextFaciltyId: String = thirdNextExclusion.facility_id

        val faciltiyAndOptionsList = mutableListOf<FacilityAndOptions>()
        val optionsLists = mutableListOf<String>()

        mViewModel.propertyData.observe(this, Observer { propertyData ->
            propertyData.facilities.forEach { facility ->
                val opt = FacilityAndOptions(
                    facility_id = facility.facility_id,
                    name = facility.name,
                    option = facility.options
                )
                propertyData.exclusions.forEach {
                    it.forEach { exclusion ->
                        val optionIdList = mutableListOf<String>()
                        optionIdList.add(exclusion.options_id)
                        optionsLists.add(exclusion.options_id)
                    }
                }
                faciltiyAndOptionsList.add(opt)

            }

            var map: MutableMap<String, List<FacilityAndOptions>> = mutableMapOf()
            for (data in faciltiyAndOptionsList) {
                val key = data.facility_id
                if (!map.containsKey(key)) {
                    val listData = mutableListOf<FacilityAndOptions>()
                    listData.add(data)
                    map.put(data.facility_id, listData)
                } else {
                    map.put(key, faciltiyAndOptionsList)
                }
            }

            val consolidatedList = mutableListOf<ListItem>()
            for (facility_id in map.keys) {
                val headerItem = HeaderId(facility_id)
                consolidatedList.add(headerItem)
                map.get(facility_id)?.let {
                    for (response in it) {
                        response.option.forEach { option ->
//                            if (facility_id == firstFaciltyId && option.id == firstOptionsId) {
//
//                            } else if (facility_id == secondFaciltyId && option.id == secondOptionsId) {
//
//                            } else if (facility_id == thirdFaciltyId && option.id == thirdOptionsId) {
//
//                            } else if (facility_id == thirdNextFaciltyId && option.id == thirdNextOptionsId) {
//
//                            } else if (facility_id == firstNextFaciltyId && option.id == firstNextOptionsId) {
//
//                            } else if (facility_id == secondNextFaciltyId && option.id == secondNextOptionsId) {
//
//                            } else {
                                val optionData = GeneralItems(option)
                                consolidatedList.add(optionData)
                           // }

                        }
                    }
                }
                mAdapter = MyPropertyRecyclerView()
                mAdapter.setData(consolidatedList)
                mRecyclerView.adapter = mAdapter
            }

        })
    }
}