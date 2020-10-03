import 'package:flutter/material.dart';
import 'package:toast/toast.dart';
import 'package:back_button_interceptor/back_button_interceptor.dart';


import 'Field.dart';

class tictactoe extends StatefulWidget{

  @override
  State<StatefulWidget> createState() => Game();

}

class Game extends State<tictactoe> {

  String cross = "X";
  int _moves=0;
  List<String> board = ["","","","","","","","",""];
  String symbol;
  Map<String,String> players = {"One":"X","Two":"O"};
  var _currentplayer="One";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text("Tic Tac Toe"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.all(60),
              child: Text(
                "Player $_currentplayer plays "+ players[_currentplayer], style: TextStyle(fontSize: 25),),
            ),
            Expanded(
                child:
                GridView.builder(
                  itemCount: board.length,
                  physics: NeverScrollableScrollPhysics(),
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 3
                  ),
                  itemBuilder: (BuildContext context, int index){
                        return GestureDetector(
                        onTap: (){
                          setState(() {
                            if (board[index]=="") {
                              _moves++;
                              board[index] = players[_currentplayer];
                              if(_moves==9){
                                _showDialog("It's a draw.");
                              }
                              if(!_win(players[_currentplayer])){
                                if(_currentplayer=="One"){
                                  _currentplayer="Two";
                                }
                                else{
                                  _currentplayer="One";
                                }
                              }
                              else{
                                _showDialog("Player $_currentplayer wins.");
                              }
                            }
                            else{
                              Toast.show("You cannot play here", context,duration:Toast.LENGTH_LONG ,gravity: Toast.BOTTOM);
                            }
                          });
                        },
                          child:Container(
                            margin: const EdgeInsets.all(0.0),
                            decoration: BoxDecoration(
                                border: _determineBorder(index)
                            ),
                            child:
                        Center(child: Text(board[index],
                          style: TextStyle(
                            fontSize: 40,
                            color: Colors.blue,
                          ),
                        ),
                        ),
                        )
                    );
                  },
                ),
              ),
          ],
        ),
      ),
    );
  }
  final BorderSide _borderSide = BorderSide(
      color: Colors.blueAccent,
      width: 2.0,
      style: BorderStyle.solid
  );

  Border _determineBorder(idx){
    Border determinedBorder = Border.all();

    switch(idx) {
      case 0:
        determinedBorder = Border(bottom: _borderSide, right: _borderSide);
        break;
      case 1:
        determinedBorder = Border(left: _borderSide, bottom: _borderSide, right: _borderSide);
        break;
      case 2:
        determinedBorder = Border(left: _borderSide, bottom: _borderSide);
        break;
      case 3:
        determinedBorder = Border(bottom: _borderSide, right: _borderSide, top: _borderSide);
        break;
      case 4:
        determinedBorder = Border(left: _borderSide, bottom: _borderSide, right: _borderSide, top: _borderSide);
        break;
      case 5:
        determinedBorder = Border(left: _borderSide, bottom: _borderSide, top: _borderSide);
        break;
      case 6:
        determinedBorder = Border(right: _borderSide, top: _borderSide);
        break;
      case 7:
        determinedBorder = Border(left: _borderSide, top: _borderSide, right: _borderSide);
        break;
      case 8:
        determinedBorder = Border(left: _borderSide, top: _borderSide);
        break;
    }

    return determinedBorder;
  }

  bool _win(symbol){
    if (_moves>4) {
      if(board[0]==symbol && board[1]==symbol && board[2]==symbol){
        return true;
      }
      else if(board[0]==symbol && board[3]==symbol && board[6]==symbol){
        return true;
      }
      else if(board[0]==symbol && board[4]==symbol && board[8]==symbol){
        return true;
      }
      else if(board[2]==symbol && board[5]==symbol && board[8]==symbol){
        return true;
      }
      else if(board[2]==symbol && board[4]==symbol && board[6]==symbol){
        return true;
      }
      else if(board[6]==symbol && board[7]==symbol && board[8]==symbol){
        return true;
      }
      else if(board[1]==symbol && board[4]==symbol && board[7]==symbol){
        return true;
      }
      else if(board[3]==symbol && board[4]==symbol && board[5]==symbol){
        return true;
      }
      else{
        return false;
      }
    }
    else{
      return false;
    }
  }

  void _showDialog(msg) {
    // flutter defined function
    showDialog(
      context: context,
      builder: (BuildContext context) {
        // return object of type Dialog
        return AlertDialog(
          title: new Text("Game Finished"),
          content: new Text(msg),
          actions: <Widget>[
            // usually buttons at the bottom of the dialog
            new FlatButton(
              child: new Text("Close"),
              onPressed: () {
                Navigator.popUntil(context, ModalRoute.withName('/'));
              },
            ),
          ],
        );
      },
    );
  }
}