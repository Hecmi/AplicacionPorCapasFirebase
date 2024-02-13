
from django.urls import path
from .views import *

urlpatterns = [
    path('agregarcliente/', agregarcliente, name='agregarcliente'),
    path('agregarventas/', agregarventas, name='agregarventas'),
    path('producto_sucursales/',producto_sucursales, name='producto_sucursales'),
    path('ver_producto_sucursales/',ver_producto_sucursales, name='ver_producto_sucursales'),
    path('ver_cliente/', ver_cliente, name='ver_cliente'),
    path('ver_ventas/', ver_ventas, name='ver_ventas'),
    path('buscar_cliente/', buscar_cliente, name='buscar_cliente'),  # Añade esta línea para la búsqueda
    path('buscar_producto/', buscar_producto, name='buscar_producto'),  # Añade esta línea para la búsqueda
    path('buscar_venta/', buscar_venta, name='buscar_venta'),  # Añade esta línea para la búsqueda
    path('venta/editar_venta/<int:id_venta>/', editar_venta, name='editar_venta'),
    path('venta/eliminar/<id_venta>', eliminar_venta, name='eliminar_venta'),
    path('cliente/editar_cliente/<identificacion>/', editar_cliente, name='editar_cliente'),
    path('cliente/eliminar/<identificacion>', eliminar_cliente, name='eliminar_cliente'),
    path('producto/editar_producto_sucursales/<int:id_producto>/', editar_producto_sucursales, name='editar_producto_sucursales'),
    path('producto/eliminar/<id_producto>', eliminar_producto, name='eliminar_producto')


]
