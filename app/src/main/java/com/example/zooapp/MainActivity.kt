package com.example.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load animals
        listOfAnimals.add(Animal("Baboon","Descendant to a Monkey. Important character in Lion king Movie",R.drawable.baboon,false))
        listOfAnimals.add(Animal("BullDog","A dangerous dog breed",R.drawable.bulldog,false))
        listOfAnimals.add(Animal("Panda","Lovely and Dumb Animal",R.drawable.panda,false))
        listOfAnimals.add(Animal("Swallow Bird","I don't know its description",R.drawable.swallow_bird,false))
        listOfAnimals.add(Animal("White Tiger","Brother of Orange Tiger. Also a Marvel Comic Character",R.drawable.white_tiger,true))
        listOfAnimals.add(Animal("Zebra","The animal whose imprints are found on every road",R.drawable.zebra,false))

        adapter = AnimalsAdapter(this,listOfAnimals)
        tvListAnimal.adapter = adapter
    }

    // method to add or delete an item from the list view
//    fun addDelete(index:Int){
//        listOfAnimals.removeAt(index)   // to remove
//        listOfAnimals.add(index,.........)    // to add
//        adapter!!.notifyDataSetChanged()
//    }

    inner class AnimalsAdapter:BaseAdapter {

        var listOfAnimals = ArrayList<Animal>()
        var context:Context?=null

        constructor(context:Context, listOfAnimals:ArrayList<Animal>):super(){
            this.context=context
            this.listOfAnimals = listOfAnimals
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

        override fun getItem(p0: Int): Any {   // not using this method in the app
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long { //not using this method in the app
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = listOfAnimals[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView:View?
            if(animal.isKiller==true){
                myView = inflator.inflate(R.layout.animal_killer_ticket,null)
            }else{
                myView = inflator.inflate(R.layout.animal_ticket,null)
            }

            myView.txtVName.text = animal.name
            myView.txtVDesc.text = animal.desc
            myView.imgVAnimalImage.setImageResource(animal.image!!)

            myView.imgVAnimalImage.setOnClickListener {
//                addDelete(p0)
                val intent = Intent(context,AnimalInfo::class.java)
                intent.putExtra("name",animal.name)
                intent.putExtra("desc",animal.desc)
                intent.putExtra("image",animal.image)
                context!!.startActivity(intent)
            }
            return myView
        }

    }

}