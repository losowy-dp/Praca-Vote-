package com.example.vote_01.Activity

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vote_01.Fragment.BottomMenuContent
import com.example.vote_01.Fragment.ButtonGroup
import com.example.vote_01.Fragment.standartQuad
import com.example.vote_01.R
import com.example.vote_01.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            ListGroup(groups = listOf(
                ButtonGroup(
                    title = "Me group 1",
                    DarkYellow,
                    Yellow,
                    LightYellow
                ),
                ButtonGroup(
                    title = "Me group 2",
                    DarkRed,
                    Red,
                    LightRed
                ),
                ButtonGroup(
                    title = "Me group 3",
                    DarkBlue,
                    Blue,
                    LightBlue
                ),
                ButtonGroup(
                    title = "Me group 4",
                    DarkBlue,
                    Blue,
                    LightBlue
                ),
                ButtonGroup(
                    title = "Me group 5",
                    DarkGreen,
                    Green,
                    LightGreen
                )
            ))
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Groups", R.mipmap.ic_list_foreground),
            BottomMenuContent("Profile", R.mipmap.ic_account_foreground)),
            modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@ExperimentalFoundationApi
@Composable
fun ListGroup(groups: List<ButtonGroup>) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBlue)
            ) {
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        text = "My Groups",
                        style = MaterialTheme.typography.h3,
                        color = WhiteText,
                        modifier = Modifier
                            .padding(15.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 7.5.dp, bottom = 100.dp),
                modifier = Modifier.fillMaxHeight()
            )
            {
                items(groups.size + 1)
                {
                    if (it != groups.size)
                        groupBlock(buttonGroup = groups[it])
                    else
                        newGroup()
                }
            }
        }
}

//Create item group block

@Composable
fun groupBlock(buttonGroup: ButtonGroup) {
    BoxWithConstraints(modifier = Modifier
        .padding(7.5.dp)
        .aspectRatio(1f)
        .clip(RoundedCornerShape(10.dp))
        .background(buttonGroup.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //medium color path point
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply{
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standartQuad(mediumColoredPoint1,mediumColoredPoint2)
            standartQuad(mediumColoredPoint2,mediumColoredPoint3)
            standartQuad(mediumColoredPoint3,mediumColoredPoint4)
            standartQuad(mediumColoredPoint4,mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        //light color path point
        val lightColoredPoint1 = Offset(0f, height * 0.35f)
        val lightColoredPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightColoredPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightColoredPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightColoredPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

         val lightColoredPath = Path().apply {
            moveTo(lightColoredPoint1.x, lightColoredPoint1.y)
            standartQuad(lightColoredPoint1, lightColoredPoint2)
            standartQuad(lightColoredPoint2, lightColoredPoint3)
            standartQuad(lightColoredPoint3, lightColoredPoint4)
            standartQuad(lightColoredPoint4, lightColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            drawPath(path = mediumColoredPath, color = buttonGroup.mediumColor)
            drawPath(path = lightColoredPath, color = buttonGroup.ligthColor)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        )
        {
            Text (
                text = buttonGroup.title,
                style = MaterialTheme.typography.h5,
                color = Color.White,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Text(
                text = ">",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .clickable {
                        //todo group actyvity
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(AppbarColor)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

@Composable
fun newGroup() {
    BoxWithConstraints(modifier = Modifier
        .padding(7.5.dp)
        .aspectRatio(1f)
        .clip(RoundedCornerShape(10.dp))
        .background(WhiteText)
    ) {Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    )
    {
        Text (
            text = "Add new group",
            style = MaterialTheme.typography.h6,
            color = Color.Black,
            lineHeight = 26.sp,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = "+",
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier
                .clickable {
                    //todo new group actyvity
                }
                .align(Alignment.Center)
                .clip(RoundedCornerShape(50.dp))
                .background(AppbarColor)
                .padding(vertical = 6.dp, horizontal = 15.dp)
        )
    }}
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHileghtColor: Color = LightBlue,
    activeTextColor: Color = WhiteText,
    inactiveTextColor: Color = LightBlue,
    initialSelectedItemIndex: Int = 0
){
    var selectedItemIndex by remember{
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DarkBlue)
            .padding(15.dp)
    ){
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHileghtColor = activeHileghtColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ){
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHileghtColor: Color = LightBlue,
    activeTextColor: Color = WhiteText,
    inactiveTextColor: Color = LightBlue,
    onItemClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable
        {
            onItemClick() //todo Click
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHileghtColor else Color.Transparent)
                .padding(10.dp)
        ){
            Icon(painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if(isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier
                    .size(20.dp))
        }
        Text(
            text = item.title,
            color = if(isSelected) activeHileghtColor else inactiveTextColor
        )
    }
}