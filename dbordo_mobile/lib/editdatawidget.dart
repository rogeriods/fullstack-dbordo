import 'package:dbordo_mobile/services/api_service.dart';
import 'package:flutter/material.dart';

import 'models/historico.dart';

class EditDataWidget extends StatefulWidget {
  EditDataWidget(this.historico);
  final Historico historico;

  @override
  _EditDataWidgetState createState() => _EditDataWidgetState();
}

class _EditDataWidgetState extends State<EditDataWidget> {
  _EditDataWidgetState();

  final ApiService api = ApiService();
  final _addFormKey = GlobalKey<FormState>();
  final _kmChegadaController = TextEditingController();

  @override
  void initState() {
    _kmChegadaController.text = widget.historico.kmChegada.toString();
    super.initState();
  }

  String getVeiculo(Object veiculo) {
    var my = Map<String, dynamic>.from(veiculo);
    return my['modelo'].toString() + ' - ' + my['placa'].toString();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Retorno do Veículo'),
      ),
      body: Form(
        key: _addFormKey,
        child: SingleChildScrollView(
          child: Container(
            padding: EdgeInsets.all(20.0),
            child: Card(
                child: Container(
                    padding: EdgeInsets.all(10.0),
                    width: 440,
                    child: Column(
                      children: <Widget>[
                        Container(
                          margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                          child: Column(
                            children: <Widget>[
                              Text('Veículo'),
                              TextField(
                                obscureText: true,
                                enabled: false,
                                decoration: InputDecoration(
                                  border: OutlineInputBorder(),
                                  labelText: getVeiculo(widget.historico.veiculo),
                                ),
                              ),
                            ],
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                          child: Column(
                            children: <Widget>[
                              Text('Condutor'),
                              TextField(
                                obscureText: true,
                                enabled: false,
                                decoration: InputDecoration(
                                  border: OutlineInputBorder(),
                                  labelText: widget.historico.condutor,
                                ),
                              ),
                            ],
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.fromLTRB(0, 0, 0, 10),
                          child: Column(
                            children: <Widget>[
                              Text('Km Chegada'),
                              TextFormField(
                                controller: _kmChegadaController,
                                decoration: const InputDecoration(
                                  hintText: 'Km Chegada',
                                ),
                                validator: (value) {
                                  if (value.isEmpty) {
                                    return 'Please enter km chegada';
                                  }
                                  return null;
                                },
                                onChanged: (value) {},
                              ),
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
                                  if (_addFormKey.currentState.validate()) {
                                    _addFormKey.currentState.save();
                                    api.updateHistorico(widget.historico.id.toString(),
                                        Historico(kmChegada: int.parse(_kmChegadaController.text)));
                                    int count = 0;
                                    Navigator.of(context).popUntil((_) => count++ >= 2);
                                    //Navigator.pop(context);
                                  }
                                },
                                child: Text('Gravar', style: TextStyle(color: Colors.white)),
                                color: Colors.blue,
                              )
                            ],
                          ),
                        ),
                      ],
                    ))),
          ),
        ),
      ),
    );
  }
}
