package com.example.mvvm.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.Model.Product

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        // Initialize with sample data
        _products.value = listOf(
            Product("Pullover", "Mango", 4.0f, 51.0f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzuEo1z1bWCkhy9bfAa2ZMrqEoJ_RIKZUMXA&s"),
            Product("Blouse", "Dorothy Perkins", 0.0f, 34.0f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2z25zZpD9eW0MwCuyxLAxpxshSe1onvUg6g&s"),
            Product("T-shirt", "LOST Ink", 5.0f, 12.0f, "https://m.media-amazon.com/images/I/716YrDEnNSL._AC_UY1100_.jpg"),
            Product("Shirt", "Topshop", 3.0f, 51.0f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwXhduLP4zRo8dGVEbzVn948b34Z6iQoETpQ&s")
        )
    }
}