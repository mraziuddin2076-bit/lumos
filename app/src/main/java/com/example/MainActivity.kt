package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.theme.MyApplicationTheme

data class Movie(
    val title: String,
    val year: String,
    val rating: String,
    val description: String,
    val imageUrl: String
)

val movies = listOf(
    Movie("The Sorcerer's Stone", "2001", "4.8", "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.", "https://lh3.googleusercontent.com/aida-public/AB6AXuC9L29CagYr0Hvt_Hwt1ayK6qq6YUAKBm7gdXGTJ_7BkL8GYWAF3mvKq_-j7VMhKGwtSWRG4hDGNXx9yAv4IUo6MKemoe1EfkmFe_JYyKBZbSXjBqEzyzm8YJDPiXplZtQSE2RRCcAFekcrSZ5OsetrzwDotXCrTimlU7a725C-HP3Yi59bMKiKwvIAhgdMEq3QeqBFhHagsJc0c9D0g7tvzRkZl6oOaulYqcrkgaCwYp-qF7E8xP1E-Mh2vVmlO7m4ouaWf4HOyww"),
    Movie("The Chamber of Secrets", "2002", "4.7", "An ancient prophecy seems to be coming true when a mysterious presence begins stalking the corridors of a school of magic.", "https://lh3.googleusercontent.com/aida-public/AB6AXuB2Dj1eR0RYxfHHFApVeP1S7YqMoUe2mm9Xvz-1qbLJCYHZk27XMCeAsuBlCreCfqrke7gNvMGOujZqvN3zYkFuClJo9m4T3YhPeYZHX_BKLkR_BPdLmR26355rVg60H8niY5eiF8ir3rNlgKqMwRARzzWgevuX6K-wkNRokydH_-Qg-E0oY49Xrj5WvA_pbwAwKbbPb6glOXWb3UuGYh853G01dMUCuhYLGJyYz6gYxk82dB3K8ZtkhosDQ1NH70_lso55RA9JuUY"),
    Movie("The Prisoner of Azkaban", "2004", "4.9", "Harry Potter, Ron and Hermione return to Hogwarts as teenagers, only to find themselves at the center of a mystery involving escaped convict Sirius Black.", "https://lh3.googleusercontent.com/aida-public/AB6AXuDRrxiOjhl8fM03VsZpVsx6gMZLLFyMjWnM06h0TPOtNlp1We1Q_dCbUFNY5_u1G3JRFweZp1TP13Js2JV-3z5iLGrG_QjiivHFGSkSop6LHWZ-JgLIJWmlYfYOv_4rWPV03PDVeTLzx1rc6zXvC35jyhkbhMaYG0TQ2AFMsGGIkbEJbGkwAF6zwo_empFja5bfQe0JC5g5OEO15mJSNj-ww4tcr5iZ3qM2TKateHPW2wf53QxNsUzhTz_ar4wVTLXZbeurR2xExuA"),
    Movie("The Goblet of Fire", "2005", "4.6", "Harry Potter finds himself competing in a hazardous tournament between rival schools of magic, but he is distracted by recurring nightmares.", "https://lh3.googleusercontent.com/aida-public/AB6AXuCMEhvuiEg8jGOXFj00rEfGNDsYM9IGTQWy3DXSRlJKkpBj9ebpUWNo5DPWWYnP7oETKrBpq7qFd37tn6alf5qeGOqAhgvHbDwt2lGZxxQqu6UZS843Ueto5GHNQEY65jpaCQq74Rpw2A-sb2fzejVvMQnc6i2ve8IBr_WvLoTfJzzE0GiTZ6Ucmm86qNj20iLncoUPAskknM8WdNoUGq98slamoSlaO9ajFYcnNVL-f7m9H8YhsCYi40wPFbrhCuAEAeFcXMbjOwo"),
    Movie("The Order of the Phoenix", "2007", "4.5", "With their warning about Lord Voldemort's return scoffed at, Harry and Dumbledore are targeted by the wizard authorities.", "https://lh3.googleusercontent.com/aida-public/AB6AXuCn7gIpsHKqkH0BSQ_7dgRk4_9xG2KcBWkhgYg6Ol_2EyLi0gkQ_uSQE_uJ4PpGM2-BLgp_O1cI0WLdi6zbZvb_Hdv5oG0Nyco9rOnQEHwIyrwVEA_8Bt156FNUj3PPkyKGFtfqDSf6a_8PUZy76h2rXooFb3oMsrLmOP2L2zHfqIFIY2dMk_vwEdlHSq2D2I_hwRN_hso1aQbL8sE8osyqS6h4UpPX5Lw9biaOoDRYc2O7dgpmPnS4Oys0SDJ-ocSLVOmyQFczAf4"),
    Movie("The Half-Blood Prince", "2009", "4.6", "As Harry Potter begins his sixth year at Hogwarts, he discovers an old book marked as 'the property of the Half-Blood Prince'.", "https://lh3.googleusercontent.com/aida-public/AB6AXuDKnZSxsuR4XFkrN1K_AOc_iEhvTGjAOZ8tQeSFnbhLUiMr6lOeg2Ru-VoIRKxSPT2zmiSmIK1sJQGOvYrzctJol_SNGupnsfWjLZ8YnA6GTs3G4QG0mBa4RkbKuZIqMSbF0ohfOXh9s7CD6xPc9wqCaxtoSDh6Dn9zke_H16u_DGkRioZsnjWlyGTwSXtxFPJdadXeHjy4h_4NFCuxgc7ZjSzRuDbmZN_bYDv1K-y-46FA31RkoinODhgri_es5iVDqo2RzEVno9o"),
    Movie("Deathly Hallows - Part 1", "2010", "4.7", "As Harry, Ron and Hermione race against time and evil to destroy the Horcruxes, they uncover the existence of the three most powerful objects in the wizarding world.", "https://lh3.googleusercontent.com/aida-public/AB6AXuCyh991S_xnzWOUDYjJWr8igtbwoG4HPb3fka0kBgCu7sVTXSw2FtQ6nGME_GU4ccBoqReF71-xvxH_GPCsRyQvfSU0KzltLdV804UQk6t_baYzja-JYDXd6O8pYAQyoZI0GgFJVU4RVhThPTovy3PzYLJogQCpLLwyoGwNgWvNta_l6TIO_OXHi1ZfWFMIuggHPfgkegibQgkwKoCNvDKeZvWvaid-vGZjBRa3axAEzNtSBz_hIkpx3qVYzUAAm2_OVPE59eqD1-4"),
    Movie("Deathly Hallows - Part 2", "2011", "4.9", "Harry, Ron, and Hermione search for Voldemort's remaining Horcruxes in their effort to destroy the Dark Lord as the final battle rages on at Hogwarts.", "https://lh3.googleusercontent.com/aida-public/AB6AXuD9lKCF8nG5EvpOMTiVNg1hEvI9fvzESycN5slpVxCJ5RbXqHDIJroIJZAAd33N5q8cpRYVE9ZCjqVXEhEm656BWDC7psScbpNcMdp3iNMJ_-QySo2uZP-a07XqR5ponSjtGtkBVIs4snyw7XLKi8TEnccOK0ZYiQjx_nOJxhUXD-VwoQ35hFlCXMu-75F_c6EhWE0agTyX02KhShTa3c4yygqS_L3nR4Rr5TXhHuxCaaoD8iYDwQV2hWza7WuMQ9js5GJYnwH86uM")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                LumosApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LumosApp() {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp > 600
    val columns = if (isTablet) 2 else 1

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "LUMOS",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 6.sp // 0.2em approx
                        )
                    }
                },
                navigationIcon = {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuA3zCZPRHv_ZZg2Vwe8bDcGdl5cXIKvTekqxH4gfCGHFs6f30lDOSjPJ7Nw_jmdDtr4m1aADMepR-pGD0DdSzLqPf4MQQbIEfWQ0IV2tfCfsDSXHB2GRg6kAtV1gY9ZCsu3ZZ-XhZIgzC_oBUMvhFdYMIrKm7tPnMALXxEZyp_hGiAGxRG21RB8QRsRr18hRiTVThgxYPX8vOMyHeEw_VrvzQ-qvTS9oUWT9B6ZZeg2q8v3TacDPjg0SZT2XiwjtrdDku9D6GeYiQg",
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), CircleShape),
                        contentScale = ContentScale.Crop
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
                modifier = Modifier.padding(bottom = 60.dp) // push above nav bar
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                tonalElevation = 8.dp,
                modifier = Modifier.clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Movie, contentDescription = "Movies") },
                    label = { Text("MOVIES", style = MaterialTheme.typography.labelSmall) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )
                // Using somewhat related icons since wands might not be in default icons
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "Wands") }, // Placeholder wand icon
                    label = { Text("WANDS", style = MaterialTheme.typography.labelSmall) },
                    colors = NavigationBarItemDefaults.colors(
                         selectedIconColor = MaterialTheme.colorScheme.primary,
                         selectedTextColor = MaterialTheme.colorScheme.primary,
                         unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                         unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                         indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
                    label = { Text("PROFILE", style = MaterialTheme.typography.labelSmall) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.RateReview, contentDescription = "Reviews") },
                    label = { Text("REVIEWS", style = MaterialTheme.typography.labelSmall) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = innerPadding.calculateTopPadding() + 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 80.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item(span = { GridItemSpan(columns) }) {
                Column(modifier = Modifier.padding(bottom = 32.dp)) {
                    Text(
                        text = "Film Archive",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .height(2.dp)
                            .width(96.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        Color.Transparent
                                    )
                                )
                            )
                    )
                    Text(
                        text = "Explore the cinematic journey of the boy who lived, from the hallowed halls of Hogwarts to the final battle.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(0.8f) // max-w-md approx
                    )
                }
            }

            items(movies) { movie ->
                MovieCard(movie = movie)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Bottom-to-top gradient for the image
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.8f),
                                    MaterialTheme.colorScheme.surfaceContainer
                                ),
                                startY = 50f,
                                endY = Float.POSITIVE_INFINITY // Let it naturally fade out at bottom
                            )
                        )
                )

                // Rating Pill
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                        .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = movie.rating,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(16.dp) // inset-card
            ) {
                Text(
                    text = movie.year,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
