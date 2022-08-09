import 'dart:convert';

import 'package:dbordo_mobile/models/historico.dart';
import 'package:http/http.dart';

class ApiService {
  // API url
  final String apiUrl = "http://<host>:8088";

  // Return all vehicle in transit
  Future<List<Historico>> getHistoricos() async {
    Response res = await get('$apiUrl/api/historicos', headers: {'Content-Type': "application/json; charset=utf-8"});
    if (res.statusCode == 200) {
      List<dynamic> body = json.decode(utf8.decode(res.bodyBytes));
      List<Historico> historicos = body.map((dynamic item) => Historico.fromJson(item)).toList();
      return historicos;
    } else {
      throw Exception('Falha ao carregar a lista');
    }
  }

  // Return a vehicle by id
  Future<Historico> getHistoricoById(String id) async {
    final response =
        await get('$apiUrl/api/historicos/$id', headers: {'Content-Type': "application/json; charset=utf-8"});
    if (response.statusCode == 200) {
      return Historico.fromJson(json.decode(utf8.decode(response.bodyBytes)));
    } else {
      throw Exception('Falha ao carregar o veículo');
    }
  }

  // Update a vehicle by id and data
  Future<Historico> updateHistorico(String id, Historico historico) async {
    final response = await put(
      '$apiUrl/api/historicos/$id',
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, int>{'kmChegada': historico.kmChegada}),
    );
    if (response.statusCode == 200) {
      return Historico.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to update a history');
    }
  }
}
