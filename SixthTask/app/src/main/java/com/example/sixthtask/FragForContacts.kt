package com.example.sixthtask

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthtask.MainActivity.Companion.CONTACTS
import com.example.sixthtask.databinding.FragmentFragForContactsBinding
import java.lang.RuntimeException

class FragForContacts : Fragment() {
    lateinit var binding: FragmentFragForContactsBinding
    private var contactArray: ArrayList<Person>? = null
    private var recyclerView : RecyclerView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactArray = requireArguments().getParcelableArrayList(CONTACTS)

        if (contactArray == null)
            throw RuntimeException("Error!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragForContactsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(view.context)
        recyclerView?.adapter = RecyclerAdapter(contactArray!!)
    }

    companion object {
        fun newInstance(personList: ArrayList<Person>): FragForContacts {
            val contactListFragment = FragForContacts()
            contactListFragment.arguments = Bundle().also {
                it.putParcelableArrayList(CONTACTS, personList)
            }
            return contactListFragment
        }
    }
}