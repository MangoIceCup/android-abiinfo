package party.smileface.app.abiinfo

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

fun getAbiInfo(): Array<String> {
    return arrayOf(*Build.SUPPORTED_ABIS)
}

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.abinfoList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = MyAdapter(getAbiInfo(), this)
        Log.w(TAG, "onCreate: " + getAbiInfo().contentToString())
    }
}


class MyViewHolder(itemView: View, val abinfo: TextView) : RecyclerView.ViewHolder(itemView) {
}

class MyAdapter constructor(val abinfos: Array<String>, val context: Context) :
    RecyclerView.Adapter<MyViewHolder>() {
    private  val TAG = "MainActivity"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_abi_list_item, parent, false)
        return MyViewHolder(view, view.findViewById(R.id.abiInfo))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val abi = abinfos[position]
        holder.abinfo.text = abi
    }

    override fun getItemCount(): Int {
        Log.w(TAG, "getItemCount: " + abinfos)

        return abinfos.size
    }

}