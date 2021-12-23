package com.example.sixthtask

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import kotlin.random.Random

class MainActivity : AppCompatActivity(), InterfaceClick.ClickedItemHandler {
    private var frameLayout: FrameLayout? = null
    private var contactListFragment: FragForContacts? = null
    private var contactsList: ArrayList<Person>? = null
    private val contactsListUntil = 100
    private var listNames: List<String>? = null
    private var listSurnames: List<String>? = null

    companion object {
        const val ITEM_FOR_CONTACTS = "ITEM_EXTRA"
        const val CONTACTS = "CONTACTS_EXTRA"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayout = findViewById(R.id.fragContainer)
        listNames = getNameList()
        listSurnames = getSurnameList()

        if (savedInstanceState == null) {
            contactsList = initContactList()

            supportFragmentManager.apply {
                val transaction = beginTransaction()
                contactListFragment = FragForContacts.newInstance(contactsList!!)
                transaction.replace(R.id.fragContainer, contactListFragment!!)
                transaction.commit()
            }
        }
    }

    override fun openContactDetails(contact: Person) {
        supportFragmentManager.apply {
            val transaction = beginTransaction()
            val contactDetailFragment = FragForContactsDetails.newInstance(contact)
            transaction.replace(R.id.fragContainer, contactDetailFragment)
            transaction.addToBackStack(null);
            transaction.commit()
        }
    }

    override fun updateContact(contact: Person) {
        contactsList!![contact.id].name = contact.name
        contactsList!![contact.id].secondName = contact.secondName
        contactsList!![contact.id].phone = contact.phone
    }

    private fun getNameList(): List<String> {
        return this.resources.getStringArray(R.array.names).toList()
    }

    private fun getSurnameList(): List<String> {
        return this.resources.getStringArray(R.array.secondNames).toList()
    }

    private fun initContactList(): ArrayList<Person> {
        val contactList = ArrayList<Person>()
        val random = Random(10)
        for (i in 0..contactsListUntil)
            contactList.add(Person(i, random.nextInt(contactsListUntil) + 1, listNames?.get(random.nextInt(11)), listSurnames?.get(random.nextInt(11)), "+7" + random.nextLong(9999999999).toString()))
        return contactList
    }
}