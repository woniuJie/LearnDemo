var board = new Array();
var score = 0 ;
var hasConficted = new Array();

$(document).ready(function(){
	newgame();
});

function newgame(){

	// 初始化棋盘格
	init();
	// 随机两个格子生成数字
	generateOneNumber();
	generateOneNumber();
}

function init(){
	for(var i = 0 ; i < 4 ; i++ )
	{
		board[i] = new Array();
		hasConficted[i] = new Array();
		for(var j = 0 ; j < 4 ; j++ )
		{
			board[i][j] = 0 ;
			hasConficted[i][j] = false ;
			var gridCell = $("#grid-cell-"+i+"-"+j);
			gridCell.css('top',getPosTop(i,j));
			gridCell.css('left',getPosLeft(i,j));
		}

	}

	updateBoardView();
	score = 0 ;
}

function updateBoardView()
{
	$(".number-cell").remove();
	for(var i = 0 ; i < 4 ; i++ )
		for(var j = 0 ; j < 4 ; j++ )
		{
			$("#grid-container").append('<div class="number-cell" id="number-cell-'+i+'-'+j+'"></div>')
			var theNumberCell = $('#number-cell-'+i+'-'+j);
			if(board[i][j] == 0)
			{
				theNumberCell.css('width','0px');
				theNumberCell.css('height','0px');
				theNumberCell.css('top',getPosTop(i,j) + 50);
				theNumberCell.css('left',getPosLeft(i,j) + 50);
			}
			else
			{
				theNumberCell.css('width','100px');
				theNumberCell.css('height','100px');
				theNumberCell.css('top',getPosTop(i,j));
				theNumberCell.css('left',getPosLeft(i,j));
				theNumberCell.css('background-color',getNumberBackgroundColor(board[i][j]));
				theNumberCell.css('color',getNumberColor(board[i][j]));
				theNumberCell.text(board[i][j]);
			}

			hasConficted[i][j] = false ;
		}
}


function generateOneNumber()
{
	if(nospace(board))
		return false ; 
	// 随机一个位
	var randx = getRand44Num() ;
	var randy = getRand44Num() ;

	var times = 0 ;
	while(times < 50){
		if(board[randx][randy] == 0)
			break;

		var randx = getRand44Num() ;
		var randy = getRand44Num() ;
		times++ ;
	}

	if(times == 50)
	{
		for(var i = 0 ; i < 4 ; i++ )
		{
			for(var j = 0 ; j < 4 ; j++ )
			{
				if(board[i][j] == 0)
				{
					randx = i ; 
					randy = j ;
				}

			}
		}
	}
	
	// 随机一个数字
	var randNumber = getRand24Num();
	board[randx][randy] = randNumber;
	showNumberWithAnimation(randx , randy , randNumber);
	return true ;
}

var startx, starty;
//获得角度
function getAngle(angx, angy) {
	return Math.atan2(angy, angx) * 180 / Math.PI;
};

//根据起点终点返回方向 1向上 2向下 3向左 4向右 0未滑动
function getDirection(startx, starty, endx, endy) {
	var angx = endx - startx;
	var angy = endy - starty;
	var result = 0;

	//如果滑动距离太短
	if (Math.abs(angx) < 2 && Math.abs(angy) < 2) {
		return result;
	}

	var angle = getAngle(angx, angy);
	if (angle >= -135 && angle <= -45) {
		result = 1;
	} else if (angle > 45 && angle < 135) {
		result = 2;
	} else if ((angle >= 135 && angle <= 180) || (angle >= -180 && angle < -135)) {
		result = 3;
	} else if (angle >= -45 && angle <= 45) {
		result = 4;
	}

	return result;
}

var startx, starty;
//获得角度
function getAngle(angx, angy) {
	return Math.atan2(angy, angx) * 180 / Math.PI;
};

//根据起点终点返回方向 1向上 2向下 3向左 4向右 0未滑动
function getDirection(startx, starty, endx, endy) {
	var angx = endx - startx;
	var angy = endy - starty;
	var result = 0;

	//如果滑动距离太短
	if (Math.abs(angx) < 2 && Math.abs(angy) < 2) {
		return result;
	}

	var angle = getAngle(angx, angy);
	if (angle >= -135 && angle <= -45) {
		result = 1;
	} else if (angle > 45 && angle < 135) {
		result = 2;
	} else if ((angle >= 135 && angle <= 180) || (angle >= -180 && angle < -135)) {
		result = 3;
	} else if (angle >= -45 && angle <= 45) {
		result = 4;
	}

	return result;
}
//手指接触屏幕
document.addEventListener("touchstart", function(e) {
	startx = e.touches[0].pageX;
	starty = e.touches[0].pageY;
}, false);
//手指离开屏幕
document.addEventListener("touchend", function(e) {
	var endx, endy;
	endx = e.changedTouches[0].pageX;
	endy = e.changedTouches[0].pageY;
	var direction = getDirection(startx, starty, endx, endy);
	switch (direction) {
		case 0:
			break;
		case 1:
			if(moveUp(board)){
				generateOneNumber();
				isgameover();
			}
			break;
		case 2:
			if(moveDown(board)){
				generateOneNumber();
				isgameover();
			}

			break;
		case 3:
			if(moveLeft(board)){
				generateOneNumber();
				isgameover();
			}

			break;
		case 4:
			if(moveRight(board)){
				generateOneNumber();
				isgameover();
			}

			break;
		default:
	}
}, false);


// $(document).keydown(function(event){
// 	switch(event.keyCode){
// 		case 37: // left
// 			if(moveLeft(board)){
// 				generateOneNumber();
// 				isgameover();
// 			}
// 			break ;
// 		case 38: // up
// 			if(moveUp(board)){
// 				generateOneNumber();
// 				isgameover();
// 			}
// 			break;
// 		case 39: // right
// 			if(moveRight(board)){
// 				generateOneNumber();
// 				isgameover();
// 			}
// 			break;
// 		case 40: // down
// 			if(moveDown(board)){
// 				generateOneNumber();
// 				isgameover();
// 			}
// 			break;
// 		default:
// 			break;
// 	}
// });

function moveLeft(board){
	if(!canMoveLeft(board))
		return false ;

	for(var i = 0 ; i < 4 ; i++ )
		for(var j = 1 ; j < 4 ; j++ )
		{
			if(board[i][j] != 0)
			{
				for(var k = 0 ; k < j ; k++)
				{
					if(board[i][k] == 0 && noBlockHorizontal(i,k,j,board))
					{
						// move
						showMoveAnimation(i,j,i,k);
						board[i][k] = board[i][j];
						board[i][j] = 0 ;
						continue ;
					}
					else if(board[i][k] == board[i][j] && noBlockHorizontal(i,k,j,board) && !hasConficted[i][k])
					{
						// move  add
						showMoveAnimation(i,j,i,k);
						board[i][k] += board[i][j];
						board[i][j] = 0 ;
						score += board[i][k] ;
						updateScore(score);
						hasConficted[i][k] = true ;
						continue ;
					}
				}
			}
		}
	setTimeout("updateBoardView()",200);
	return true ;
}


function moveRight(board){
	if(!canMoveRight(board))
		return false ;

	for(var i = 0 ; i < 4 ; i++ )
		for(var j = 2 ; j >= 0 ; j-- )
		{
			if(board[i][j] != 0)
			{
				for(var k = 3 ; k > j ; k--)
				{
					if(board[i][k] == 0 && noBlockHorizontal(i,k,j,board))
					{
						// move
						showMoveAnimation(i,j,i,k);
						board[i][k] = board[i][j];
						board[i][j] = 0 ;
						continue ;
					}
					else if(board[i][k] == board[i][j] && noBlockHorizontal(i,k,j,board) && !hasConficted[i][k])
					{
						// move  add
						showMoveAnimation(i,j,i,k);
						board[i][k] += board[i][j];
						board[i][j] = 0 ;
						score += board[i][k] ;
						updateScore(score);
						hasConficted[i][k] = true ;
						continue ;
					}
				}
			}
		}
	setTimeout("updateBoardView()",200);
	return true ;
}

function moveUp(board){
	if(!canMoveUp(board))
		return false ;

	for(var j = 0 ; j < 4 ; j++ )
		for(var i = 1 ; i < 4 ; i++ )
		{
			if(board[i][j] != 0)
			{
				for(var k = 0 ; k < i ; k++)
				{
					if(board[k][j] == 0 && noBlockVertical(j,i,k,board))
					{
						// move
						showMoveAnimation(i,j,k,j);
						board[k][j] = board[i][j];
						board[i][j] = 0 ;
						continue ;
					}
					else if(board[k][j] == board[i][j] && noBlockVertical(j,i,k,board) && !hasConficted[k][j])
					{
						// move  add
						showMoveAnimation(i,j,k,j);
						board[k][j] += board[i][j];
						board[i][j] = 0 ;
						score += board[k][j] ;
						updateScore(score);
						hasConficted[k][j] = true ;
						continue ;
					}
				}
			}
		}
	setTimeout("updateBoardView()",200);
	return true ;
}

function moveDown(board){
	if(!canMoveDown(board))
		return false ;

	for(var j = 0 ; j < 4 ; j++ )
		for(var i = 2 ; i >= 0 ; i-- )
		{
			if(board[i][j] != 0)
			{
				for(var k = 3 ; k > i ; k--)
				{
					if(board[k][j] == 0 && noBlockHorizontal(j,i,k,board))
					{
						// move
						showMoveAnimation(i,j,k,j);
						board[k][j] = board[i][j];
						board[i][j] = 0 ;
						continue ;
					}
					else if(board[k][j] == board[i][j] && noBlockHorizontal(j,i,k,board) && !hasConficted[k][j])
					{
						// move  add
						showMoveAnimation(i,j,k,j);
						board[k][j] += board[i][j];
						board[i][j] = 0 ;
						score += board[k][j] ;
						updateScore(score);
						hasConficted[k][j] = true ;
						continue ;
					}
				}
			}
		}
	setTimeout("updateBoardView()",200);
	return true ;
}

function isgameover()
{
	if(nospace(board) && nomove(board))
	{
		gameover();
	}
}

function gameover(){
	alert("game over!");
}

