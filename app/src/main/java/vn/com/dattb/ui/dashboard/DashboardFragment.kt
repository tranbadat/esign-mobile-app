package vn.com.dattb.ui.dashboard

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import vn.com.dattb.R
import vn.com.dattb.adapters.DashboardAdapter
import vn.com.dattb.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
//
//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val dashboardItems = listOf(
            DashboardItem(R.drawable.ic_incoming, "Danh sách chứng thư số"),
            DashboardItem(R.drawable.ic_incoming, "Danh sách xác thực ký"),
            DashboardItem(R.drawable.ic_incoming, "Tạo yêu cầu ký"),
            DashboardItem(R.drawable.ic_incoming, "Thiết lập chữ ký"),
            DashboardItem(R.drawable.ic_incoming, "Hủy đăng ký thiết bị"),
            DashboardItem(R.drawable.ic_incoming, "Quản lý thiết bị"),
        )

        val recyclerView = root.findViewById<RecyclerView>(R.id.dashboardRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 3) // 3 columns in the grid
        recyclerView.adapter = DashboardAdapter(dashboardItems) { item ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log and toast
                val msg = "Token from Firebase: $token"
                Log.d(TAG, msg)
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            })
//            Toast.makeText(context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}