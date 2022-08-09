import 'package:dbordo_mobile/historicolist.dart';
import 'package:dbordo_mobile/models/historico.dart';
import 'package:dbordo_mobile/services/api_service.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter DBordo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Autorização de Uso'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  // API and object variable
  final ApiService api = ApiService();
  List<Historico> historicosList;

  @override
  Widget build(BuildContext context) {
    if (historicosList == null) {
      historicosList = List<Historico>();
    }

    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: new Container(
          child: new Center(
              child: new FutureBuilder(
            future: loadList(),
            builder: (context, snapshot) {
              return historicosList.length > 0
                  ? new HistoricoList(historicos: historicosList)
                  : new Center(
                      child: new Text('Nenhum veículo reservado para liberação!',
                          style: Theme.of(context).textTheme.title));
            },
          )),
        ));
  }

  // Load all vehicle in transit
  Future loadList() {
    Future<List<Historico>> futureHistoricos = api.getHistoricos();
    futureHistoricos.then((historicosList) {
      setState(() {
        this.historicosList = historicosList;
      });
    });
    return futureHistoricos;
  }
}
