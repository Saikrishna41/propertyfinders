package com.techfortyone.propertyfinders.data.model

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class FacilityMainRealm : RealmObject() {
    var exclusions : RealmList<FacilityRealm>? = null
    var facility : RealmList<ExclusionItemsRealm>? = null

}
open class FacilityRealm : RealmObject() {
    var facility_id: String? = null
    var name: String? = null
    var options: RealmList<FacilityOptionRealm>? = null
}


open class FacilityOptionRealm : RealmObject() {
    var name: String? = null
    var icon: String? = null
    var id: String? = null
}

open class ExclusionItemsRealm : RealmObject() {
    var facilityId : String? = null
    var optionsId : String? = null
}

open class ExclusionRealm : RealmObject() {
    var exclusions: RealmList<ExclusionItemsRealm>? = null
}