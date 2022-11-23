package com.buvgm.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.buvgm.R
import com.buvgm.data.model.local.entity.Place
import okio.utf8Size

class ProductAdapter(
    private val dataSet: MutableList<Place>,
    private val listener: RecyclerViewProductEvents
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(
        val view: View,
        val listener: RecyclerViewProductEvents
    ) : RecyclerView.ViewHolder(view) {
        private val layoutProduct: ConstraintLayout = view.findViewById(R.id.layout_itemProduct)
        //private val imgProd: ImageView = view.findViewById(R.id.product_imageView)
        private val nameTxtView: TextView = view.findViewById(R.id.name_product_textView)
        private val descripcionTxtView: TextView = view.findViewById(R.id.description_textView)
        private val precioTxtView: TextView = view.findViewById(R.id.precio_textView)


        fun setData(producto: Place){
            producto.apply {
                //Asignacion de imagen
                /*imgProd.load(producto.idIMG){
                    // Carga de imagen de firebase storage
                    transformations(CircleCropTransformation())
                    error(R.drawable.ic_error)
                    memoryCachePolicy(CachePolicy.DISABLED)
                }*/
                // Asignacion de nombre
                nameTxtView.text = producto.nombre
                // Asignacion de descripcion
                var resizeDescript: String = ""
                if (producto.descripcion.utf8Size() > 20){
                    for (char in producto.descripcion){
                        resizeDescript = resizeDescript + char
                        if (resizeDescript.utf8Size() <= 20){
                            resizeDescript = resizeDescript + "..."
                            break
                        }
                    }
                }else{
                    resizeDescript = resizeDescript + "..."
                }
                descripcionTxtView.text = resizeDescript
                // asignacion de precio
                precioTxtView.text = producto.precio
                // Asignacion favoritos
                /*if (producto == true){
                    // Se deja la animacion como favorito
                }else{
                    // Se deja la animacion inicial como no favorita
                }*/
                layoutProduct.setOnClickListener{
                    listener.onItemClicked(producto)
                }

            }
        }
    }

    interface RecyclerViewProductEvents{
        fun onItemClicked(producto: Place)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).
                inflate(R.layout.item_product_list,parent,false)
        return ViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

