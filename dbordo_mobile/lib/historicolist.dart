import 'package:flutter/material.dart';

import 'detailwidget.dart';
import 'models/historico.dart';

class HistoricoList extends StatelessWidget {
  final List<Historico> historicos;
  HistoricoList({Key key, this.historicos}) : super(key: key);

  String getVeiculo(Object veiculo) {
    var my = Map<String, dynamic>.from(veiculo);
    return my['modelo'].toString() + ' - ' + my['placa'].toString();
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: historicos == null ? 0 : historicos.length,
        itemBuilder: (BuildContext context, int index) {
          return Card(
              child: InkWell(
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => DetailWidget(historicos[index])),
              );
            },
            child: ListTile(
              leading: Icon(Icons.directions_car),
              title: Text(historicos[index].condutor),
              subtitle: Text('Veículo: ' +
                  getVeiculo(historicos[index].veiculo) +
                  ' Liberado Por: ' +
                  historicos[index].liberadoPor),
              isThreeLine: true,
            ),
          ));
        });
  }
}
