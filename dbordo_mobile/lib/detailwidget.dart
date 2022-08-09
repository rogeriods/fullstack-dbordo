import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import 'editdatawidget.dart';
import 'models/historico.dart';
import 'services/api_service.dart';

class DetailWidget extends StatefulWidget {
  DetailWidget(this.historico);
  final Historico historico;

  @override
  _DetailWidgetState createState() => _DetailWidgetState();
}

class _DetailWidgetState extends State<DetailWidget> {
  _DetailWidgetState();

  final ApiService api = ApiService();

  String getVeiculo(Object veiculo) {
    var my = Map<String, dynamic>.from(veiculo);
    return my['modelo'].toString() + ' - ' + my['placa'].toString();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Detalhe'),
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: EdgeInsets.all(20.0),
          child: Card(
              child: Container(
                  padding: EdgeInsets.all(10.0),
                  width: 600,
                  child: Column(
                    children: <Widget>[
                      Container(
                        margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                        child: Column(
                          children: <Widget>[
                            Text('Condutor:', style: TextStyle(color: Colors.black.withOpacity(0.8))),
                            Text(widget.historico.condutor, style: Theme.of(context).textTheme.subtitle2)
                          ],
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                        child: Column(
                          children: <Widget>[
                            Text('Destino:', style: TextStyle(color: Colors.black.withOpacity(0.8))),
                            Text(widget.historico.destino, style: Theme.of(context).textTheme.subtitle2)
                          ],
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                        child: Column(
                          children: <Widget>[
                            Text('Liberado Por:', style: TextStyle(color: Colors.black.withOpacity(0.8))),
                            Text(widget.historico.liberadoPor, style: Theme.of(context).textTheme.subtitle2)
                          ],
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                        child: Column(
                          children: <Widget>[
                            Text('Data/Hora Saída:', style: TextStyle(color: Colors.black.withOpacity(0.8))),
                            Text(
                                DateFormat('dd/MM/yyyy').format(DateTime.parse(widget.historico.dataSaida)) +
                                    ' ' +
                                    widget.historico.horaSaida,
                                style: Theme.of(context).textTheme.subtitle2)
                          ],
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                        child: Column(
                          children: <Widget>[
                            Text('Veículo:', style: TextStyle(color: Colors.black.withOpacity(0.8))),
                            Text(getVeiculo(widget.historico.veiculo), style: Theme.of(context).textTheme.subtitle2)
                          ],
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                        child: Column(
                          children: <Widget>[
                            RaisedButton(
                              splashColor: Colors.red,
                              onPressed: () {
                                _navigateToEditScreen(context, widget.historico);
                              },
                              child: Text('Registar Retorno', style: TextStyle(color: Colors.white)),
                              color: Colors.blue,
                            ),
                          ],
                        ),
                      ),
                    ],
                  ))),
        ),
      ),
    );
  }
}

_navigateToEditScreen(BuildContext context, Historico historico) async {
  final result = await Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => EditDataWidget(historico)),
  );
}
