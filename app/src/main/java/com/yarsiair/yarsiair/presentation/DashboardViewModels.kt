import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _recommendations = MutableLiveData<List<String>>()
    val recommendations: LiveData<List<String>> get() = _recommendations

    fun fetchRecommendations(aqi: Int) {
        _recommendations.value = when {
            aqi <= 50 -> listOf(
                "Kualitas udara baik. Nikmati aktivitas luar ruangan.",
                "Berolahraga di luar sangat dianjurkan.",
                "Tetap terhidrasi"
            )
            aqi in 51..100 -> listOf(
                "Kualitas udara cukup baik. Perhatikan kondisi kesehatan Anda.",
                "Lakukan aktivitas luar ruangan dengan ringan.",
                "Tetap terhidrasi"
            )
            aqi in 101..150 -> listOf(
                "Kurangi aktivitas berat di luar ruangan.",
                "Kelompok sensitif sebaiknya tetap di dalam ruangan.",
                "Tetap terhidrasi"
            )
            aqi in 151..200 -> listOf(
                "Hindari aktivitas berat di luar ruangan.",
                "Gunakan masker jika harus keluar rumah.",
                "Tetap terhidrasi"
            )
            aqi in 201..300 -> listOf(
                "Kualitas udara sangat tidak sehat. Tetaplah di dalam ruangan.",
                "Pastikan ventilasi udara di rumah Anda baik.",
                "Tetap terhidrasi"
            )
            else -> listOf(
                "Kualitas udara berbahaya. Hindari keluar rumah.",
                "Gunakan air purifier untuk meningkatkan kualitas udara di dalam ruangan.",
                "Tetap terhidrasi"
            )
        }
    }
}
