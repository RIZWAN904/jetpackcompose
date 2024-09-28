package com.example.compose

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.NonDisposableHandle.parent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyUi()
        }
    }

    @Preview
    @Composable
    fun MyUi() {
        val scaffoldState = rememberScrollState()

        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues = it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SearchRow()
                Banner();
                Catregories()
                PopularCategoery();
                itemList();

            }
        }
    }

    data class items(
        val title: String,
        val name: String,
        val price: Int,
        val score: Double,
        val picUrl: Int
    )

    @Composable
    private fun itemList() {
        val cartoons:List<items> = listOf(
            items("The satiric half-hour adventures of\na working-class family in the misfit city of Springfield",
                " The Simpsons",300,4.6,R.drawable.c1),
            items("The fractured domestic lives of a nihilistic mad scientist \nand his anxious grandson are further complicated by their inter-dimensional misadventure",
                "Rick and Morty",200,4.9,R.drawable.c2),
            items("Follows the misadventures of four irreverent grade-schoolers\n in the quiet, dysfunctional town of South Park, Colorado.",
                "South Park",100,4.8,R.drawable.c3)
        )

        LazyRow(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cartoons) { item ->
                Column(modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
                    .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .clickable {
                        println("Clicked on : ${item.name}")
                    }
                )

                {
                    ConstraintLayout(modifier = Modifier.height(IntrinsicSize.Max)) {
                        val (topImg,title,name,catoonIcon,price,score,scoreicon) = createRefs()
                        Image(painter = painterResource(id = item.picUrl), contentDescription = null,
                            Modifier.fillMaxWidth().
                            height(180.dp).
                            constrainAs(topImg){
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }, contentScale = ContentScale.Crop
                        )
                    }
                }

            }

        }




    }



    private @Composable
    fun PopularCategoery() {
        Row(modifier = Modifier.padding(top = 36.dp, start = 16.dp, end = 16.dp)) {
            Text(text = "Popular Cartoons", color = Color(android.graphics.Color.parseColor("#2c3e50")), fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f))

            Text(text = "See all", fontWeight = FontWeight.SemiBold,
                color = Color(android.graphics.Color.parseColor("#0d86ff")),
                fontSize = 16.sp)
        }
    }

    private @Composable
    fun Catregories() {
        Row(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {
   Text(text = "Category", color = Color(android.graphics.Color.parseColor("#2c3e50")), fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
       modifier = Modifier.weight(1f))

            Text(text = "See all", fontWeight = FontWeight.SemiBold,
                color = Color(android.graphics.Color.parseColor("#0d86ff")),
                fontSize = 16.sp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)

        ) {
            Column(modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally) {
               Image(painter = painterResource(id = R.drawable.icon5), contentDescription = null,
                   modifier = Modifier
                       .size(70.dp)
                       .padding(top = 8.dp, bottom = 4.dp)
                       .background(
                           color = Color(android.graphics.Color.parseColor("#f5b7b1")),
                           shape = CutCornerShape(2.dp)
                       )
                       .padding(6.dp))

                Text(text = "Alvena",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color(android.graphics.Color.parseColor("#0d86ff")))
            }

            Column(modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.icons1), contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#f5b7b1")),
                            shape = CutCornerShape(2.dp)
                        )
                        .padding(6.dp))

                Text(text = "palvina",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color(android.graphics.Color.parseColor("#0d86ff")))
            }

            Column(modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.icon2), contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#f5b7b1")),
                            shape = CutCornerShape(2.dp)
                        )
                        .padding(6.dp))

                Text(text = "beem",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color(android.graphics.Color.parseColor("#0d86ff")))
            }

            Column(modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.icon4), contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#f5b7b1")),
                            shape = CutCornerShape(2.dp)
                        )
                        .padding(6.dp))

                Text(text = "flight",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color(android.graphics.Color.parseColor("#0d86ff")))
            }
        }
    }

    @Composable
    fun Banner() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                .height(160.dp)
                .background(
                    color = Color(android.graphics.Color.parseColor("#0d86ff")),
                    shape = RoundedCornerShape(10.dp)
                )
        )

        {
            val (img, text, button) = createRefs()

            Image(painter = painterResource(id = R.drawable.pn), contentDescription = null,
                modifier = Modifier
                    .constrainAs(img) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .size(140.dp)
                    .rotate(320f)
                    .padding(end = 16.dp, bottom = 8.dp)
            )



            Text(text = "Children Wants Mickey\nMouse Like a Rat",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(start = 16.dp, top = 16.dp)
            )

            Text(text = "Bay Now",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(android.graphics.Color.parseColor("#0d86ff")),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .constrainAs(button) {
                        top.linkTo(text.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(
                        Color(android.graphics.Color.parseColor("#ffffff")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(8.dp)
            )


        }

    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 18.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            var text by rememberSaveable { mutableStateOf("") }
            TextField(
                value = text, onValueChange = { text = it },
                placeholder = {
                    Text(
                        text = "search",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontStyle = FontStyle.Italic
                    )
                },
//              label ={Text(text= "searching", fontSize = 18.sp, color = Color.Black, fontStyle = FontStyle.Italic)},
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp), colorFilter = ColorFilter.tint(Color.Black)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    unfocusedBorderColor = Color(android.graphics.Color.parseColor("#908f90"))
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(
                        1.5.dp, color = Color(android.graphics.Color.parseColor("#FF2962FF")),
                        shape = CutCornerShape(4.dp)
                    )
                    .background(Color.White, CircleShape)
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_circle_notifications_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp, end = 4.dp)
                    .size(33.dp).background(Color(android.graphics.Color.parseColor("#0d86ff")) , CircleShape))
        }
    }


}




