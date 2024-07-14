@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mvvm.Model.Product
import com.example.mvvm.ViewModel.ProductViewModel
import com.example.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMTheme {
                val products by productViewModel.products.observeAsState(emptyList())
                Scaffold(
                    topBar = { TopBar() },
                    bottomBar = { BottomNavBar() }
                ) { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        CategoryFilters()
                        SortAndFilterOptions()
                        ProductList(products = products)
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Women's tops") },
        actions = {
            IconButton(onClick = { /* TODO: Handle search click */ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
    )
}

@Composable
fun CategoryFilters() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilterChip(text = "T-shirts")
        FilterChip(text = "Crop tops")
        FilterChip(text = "Sleeveless")
        // Add more categories as needed
    }
}

@Composable
fun FilterChip(text: String) {
    Button(
        onClick = { /* TODO: Handle filter click */ },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small
    ) {
        Text(text = text, color = Color.White)
    }
}

@Composable
fun SortAndFilterOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { /* TODO: Handle filters click */ }) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "Filters")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Filters")
        }
        Button(onClick = { /* TODO: Handle sort click */ }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Sort")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Price: lowest to high")
        }
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, style = MaterialTheme.typography.bodySmall)
            Text(text = product.brand, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            RatingBar(rating = product.rating)
            Text(text = "${product.price}$", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}



@Composable
fun RatingBar(rating: Float) {
    Row {
        repeat(5) { index ->
            Icon(
                painter = painterResource(if (index < rating) R.drawable.ic_star_filled else R.drawable.ic_star_outline),
                contentDescription = null,
                tint = Color.Yellow
            )
        }
    }
}

@Composable
fun BottomNavBar() {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            selected = false,
            onClick = { /* TODO: Handle home click */ }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Shop") },
            selected = true,
            onClick = { /* TODO: Handle shop click */ }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Bag") },
            selected = false,
            onClick = { /* TODO: Handle bag click */ }

        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites") },
            selected = false,
            onClick = { /* TODO: Handle favorites click */ }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Profile") },
            selected = false,
            onClick = { /* TODO: Handle profile click */ }
        )
    }
}
