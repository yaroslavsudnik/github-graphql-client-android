package dev.sudnik.template.data

class NetManager(/*private val applicationContext: Context*/) {
    val isConnectedToInternet: Boolean
        get() {
//            val conManaget = applicationContext.getSystemService(
//                Context.CONNECTIVITY_SERVICE
//            ) as ConnectivityManager
//            val activeNetworkInfo = conManaget.activeNetworkInfo
//            return activeNetworkInfo != null && activeNetworkInfo.isConnected
            return true
        }
}