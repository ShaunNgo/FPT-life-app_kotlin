package vn.fpt.fptlife.model

import java.io.Serializable

data class House(
    var id: String,
    var name: String,
    var address: String,
    var slug: String,
    var rogoId: String,
    var fssId: String,
    var flfCustomerId: Int,
    var provinceId: String,
    var districtId: String,
    var wardId: String,
    var isDisabled: Int,
    var createdDate: String,
    var modifiedDate: String
) : Serializable