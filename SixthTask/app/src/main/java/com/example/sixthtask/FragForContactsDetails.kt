package com.example.sixthtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.sixthtask.MainActivity.Companion.ITEM_FOR_CONTACTS
import com.squareup.picasso.Picasso
import java.lang.RuntimeException


class FragForContactsDetails : Fragment() {
    private var person: Person? = null
    private lateinit var onButtonListener: InterfaceClick.ClickedItemHandler
    private var editName: EditText? = null
    private var editSecondName: EditText? = null
    private var editPhone: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = requireArguments().getParcelable(ITEM_FOR_CONTACTS)
        onButtonListener = context as InterfaceClick.ClickedItemHandler
        if (person == null)
            throw RuntimeException("Error!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frag_for_contacts_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editName = view.findViewById(R.id.nameEditText)
        editSecondName = view.findViewById(R.id.surnameEditText)
        editPhone = view.findViewById(R.id.phoneEditText)
        editName?.setText(person?.name)
        editSecondName?.setText(person?.secondName)
        editPhone?.setText(person?.phone)

        Picasso.get().load("https://picsum.photos/id/" + person?.avatarId + "/200/200")
            .into(view.findViewById<ImageView>(R.id.avatarDetails));

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            updateContact()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onPause() {
        super.onPause()
        updateContact()
    }

    private fun updateContact() {
        person?.name = editName?.text.toString()
        person?.secondName = editSecondName?.text.toString()
        person?.phone = editPhone?.text.toString()
        onButtonListener.updateContact(person!!)
    }

    companion object {
        fun newInstance(person: Person): FragForContactsDetails {
            val contactDetailsFragment = FragForContactsDetails()
            contactDetailsFragment.arguments = Bundle().also {
                it.putParcelable(ITEM_FOR_CONTACTS, person)
            }
            return contactDetailsFragment
        }
    }
}