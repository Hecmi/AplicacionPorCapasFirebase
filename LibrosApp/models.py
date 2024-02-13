from django.db import models

class Cliente(models.Model):
    nombre = models.CharField(max_length=100)
    apellido = models.CharField(max_length=20)
    cedula = models.CharField(max_length=10)
    fecha_nacimiento = models.DateField()
    correo = models.EmailField()

class Sucursales(models.Model):
    id_sucursal = models.AutoField(primary_key=True)
    pais=models.CharField(max_length=100)
    ciudad=models.CharField(max_length=100)
    direccion=models.CharField(max_length=100)
    descripcion=models.CharField(max_length=200)


class Producto_Sucursales(models.Model):
    nombre_producto=models.CharField(max_length=100)
    stock=models.CharField(max_length=10)
    stockmin=models.CharField(max_length=10)
    stockmax=models.CharField(max_length=10)
    descripcion=models.CharField(max_length=200)
    unidadmedida=models.CharField(max_length=200)


