class Historico {
  final int id;
  final Object veiculo;
  final String condutor;
  final String destino;
  final String liberadoPor;
  final String dataSaida;
  final String horaSaida;
  final int kmChegada;

  Historico(
      {this.id,
      this.veiculo,
      this.condutor,
      this.destino,
      this.liberadoPor,
      this.dataSaida,
      this.horaSaida,
      this.kmChegada});

  factory Historico.fromJson(Map<String, dynamic> json) {
    return Historico(
        id: json['id'] as int,
        veiculo: json['veiculo'] as Object,
        condutor: json['condutor'] as String,
        destino: json['destino'] as String,
        liberadoPor: json['liberadoPor'] as String,
        dataSaida: json['dataSaida'] as String,
        horaSaida: json['horaSaida'] as String,
        kmChegada: json['kmChegada'] as int);
  }

  @override
  String toString() {
    return 'Historico{id: $id, veiculo: $veiculo, condutor: $condutor, '
        'destino: $destino, liberadoPor: $liberadoPor, dataSaida: $dataSaida, '
        'horaSaida: $horaSaida, kmChegada: $kmChegada';
  }
}
