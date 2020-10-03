import 'package:flutter/material.dart';
import 'package:toast/toast.dart';

class Field extends StatefulWidget{
  var idx;
  Field({idx});
  @override
  State<StatefulWidget> createState() => Game(idx);

}

class Game extends State<Field>{
  int idx;
  bool display;
  String symbol = "";


  Game(this.idx);
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap:(){ setState(() {
        symbol="X";
      });
      },
      child: Container(
          margin: const EdgeInsets.all(0.0),
          decoration: BoxDecoration(
            border: _determineBorder()
          ),
          child: Center(child: Text(symbol,
      style: TextStyle(
      fontSize: 20,
        color: Colors.blue,
      ),
          ),
        ),
      ),
    );
  }

  final BorderSide _borderSide = BorderSide(
      color: Colors.blueAccent,
      width: 2.0,
      style: BorderStyle.solid
  );

  Border _determineBorder(){
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
}