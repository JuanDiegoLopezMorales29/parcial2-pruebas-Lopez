# language: es
Característica: Cobro de estadías y descuentos
  Como dueño del parqueadero
  Quiero cobrar según el tiempo y el tipo de vehículo
  Para aplicar correctamente las tarifas y los beneficios

  Esquema del escenario: Cobrar una estadía
    Dado un vehículo de tipo "<tipo>"
    Cuando permanece <minutos> minutos
    Entonces debe pagar <valor> pesos

    Ejemplos:
      | tipo  | minutos | valor |
      | carro | 15      | 0     |
      | carro | 16      | 3000  |
      | carro | 61      | 6000  |
      | carro | 1440    | 25000 |
      | moto  | 15      | 0     |
      | moto  | 16      | 1500  |
      | moto  | 61      | 3000  |
      | moto  | 1440    | 12000 |

  Escenario: Aplicar el beneficio de cliente frecuente
    Dado un cobro de 3000 pesos para un cliente "frecuente"
    Cuando se aplica el descuento
    Entonces el valor final es 2700 pesos