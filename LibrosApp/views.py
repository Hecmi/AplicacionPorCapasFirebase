# nombre_de_la_aplicacion/views.py
import requests,json
from django.shortcuts import render, redirect
from django.http import HttpResponseBadRequest,HttpResponseRedirect,QueryDict
from django.urls import reverse


from .models import Cliente, Sucursales, Producto_Sucursales
#BUSCAR PRODUCTO
def buscar_producto(request):
    if request.method == 'POST':
        texto_buscar = request.POST.get('texto_buscar')
        atributo = request.POST.get('atributo') 
        print(texto_buscar)

        api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/producto/buscar?atributo={atributo}&valor={texto_buscar}'

        response = requests.get(api_url)
        print(response.status_code)

        if response.status_code == 200:
            if response.text != '':
                productos = response.json()
                return render(request, 'ver_producto_sucursales.html', {'producto_sucursales': productos})
        
    return redirect('ver_producto_sucursales')
#BUSCAR CLIENTE
def buscar_cliente(request):
    if request.method == 'POST':
        texto_buscar = request.POST.get('texto_buscar')
        atributo = request.POST.get('atributo') 
        print(texto_buscar)
        
        # Construir la URL con los parámetros de búsqueda
        api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/cliente/buscar?atributo={atributo}&valor={texto_buscar}'
        
        response = requests.get(api_url)
        print(response.status_code)

        if response.status_code == 200:
            if response.text != '':
                cliente = response.json()
                return render(request, 'ver_cliente.html', {'clientes': cliente})

    return redirect('ver_cliente')
#BUSCAR Venta
def buscar_venta(request):
    if request.method == 'POST':
        texto_buscar = request.POST.get('texto_buscar')
        atributo = request.POST.get('atributo') 
        print(texto_buscar)

        api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/venta/buscar?atributo={atributo}&valor={texto_buscar}'
        response = requests.get(api_url)
        print(response.status_code)

        if response.status_code == 200:
            if response.text != '':
                venta = response.json()
            return render(request, 'ver_ventas.html', {'ventas': venta})
        
        return redirect('ver_ventas')
#------------------------------------------------------------------------------------------------------------------------------
def agregarcliente(request):
    if request.method == 'POST':
        identificacion = request.POST.get('identificacion') 
        nombres = request.POST.get('nombres')
        apellidos = request.POST.get('apellidos')
        direccion = request.POST.get('direccion')
        email = request.POST.get('email')
        telefono = request.POST.get('telefono')
        fecha_nacimiento = request.POST.get('fecha_nacimiento')
        

        api_url = "http://26.48.208.162:8080/Ventas-Firebase/api/cliente/insertar"

        try:
            data = {
                'identificacion': identificacion,
                'nombres': nombres,
                'apellidos': apellidos,
                'direccion': direccion,
                'telefono': telefono,
                'email':email,
                'fecha_nacimiento': fecha_nacimiento
                
            }

            response = requests.post(api_url, json={'data': data}, headers={'Content-Type': 'application/json'})

            if response.status_code == 200:
                return redirect('ver_cliente')
            else:
                return HttpResponseBadRequest(f"Error al agregar cliente. Código de respuesta: {response.status_code}")

        except requests.RequestException as e:
            return HttpResponseBadRequest(f"Error al conectar con la API: {e}")
    else:
        return render(request, 'agregarcliente.html')
#---------------------------------------------------------------------------------------------
def agregarventas(request):
    if request.method == 'POST':
        id_venta=request.POST.get('id_venta')
        id_producto=request.POST.get('id_producto')
        id_cliente=request.POST.get('id_cliente')
        cantidad = request.POST.get('cantidad')
        subtotal = request.POST.get('subtotal')
        total = request.POST.get('total')  
        impuesto_aplicado = request.POST.get('impuesto_aplicado')
        descuento_aplicado = request.POST.get('descuento_aplicado')
        fecha_venta = request.POST.get('fecha_venta')

        # URL de la API para insertar venta
        api_url = "http://26.48.208.162:8080/Ventas-Firebase/api/venta/insertar"

        try:
            data = {
                'id_venta':id_venta,
                'id_producto':id_producto,
                'id_cliente':id_cliente,
                'cantidad': cantidad,
                'subtotal': subtotal,
                'total': total,  
                'impuesto_aplicado': impuesto_aplicado,
                'descuento_aplicado': descuento_aplicado,
                'fecha_venta': fecha_venta
            }

            response = requests.post(api_url, json={'data': data}, headers={'Content-Type': 'application/json'})

            if response.status_code == 200:
                return redirect('ver_ventas')
            else:
                return HttpResponseBadRequest(f"Error al agregar venta. Código de respuesta: {response.status_code}")

        except requests.RequestException as e:
            return HttpResponseBadRequest(f"Error al conectar con la API: {e}")
    else:
        return render(request, 'agregarventas.html')
#-------------------------------------------------------------------------------------------------------------------  
#Agregar Producto
def producto_sucursales(request):
    if request.method == 'POST':
        id_producto=request.POST.get('id_producto') 
        producto=request.POST.get('producto') 
        cantidad = request.POST.get('cantidad')
        unidad_medida = request.POST.get('unidad_medida')
        precio_unitario = request.POST.get('precio_unitario')
        iva = request.POST.get('iva')
        descuento = request.POST.get('descuento')
        descripcion = request.POST.get('descripcion')
        

        api_url = "http://26.48.208.162:8080/Ventas-Firebase/api/producto/insertar"

        try:
            data = {
                'id_producto': id_producto,
                'producto':producto,
                'cantidad': cantidad,
                'unidad_medida': unidad_medida,
                'precio_unitario': precio_unitario,
                'iva': iva,
                'descuento':descuento,
                'descripcion': descripcion
                
            }

            response = requests.post(api_url, json={'data': data}, headers={'Content-Type': 'application/json'})

            if response.status_code == 200:
                return redirect('ver_producto_sucursales')
            else:
                return HttpResponseBadRequest(f"Error al agregar producto. Código de respuesta: {response.status_code}")

        except requests.RequestException as e:
            
            return HttpResponseBadRequest(f"Error al conectar con la API: {e}")
    else:
        return render(request, 'producto_sucursales.html')
#---------------------------------------------------------------------------------------------   
#EDITAR VENTAS
def obtener_datos_venta(id_venta):
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/venta/get/{id_venta}'
    response = requests.get(api_url)
    
    if response.status_code == 200:
        datos_sucursal = response.json()
        return datos_sucursal
    else:
        return None

def actualizar_datos_venta(nuevos_datos):
   
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/venta/editar'
    response = requests.put(api_url,  json={'data': nuevos_datos}, headers={'Content-Type': 'application/json'})

    print(response)

    print(nuevos_datos)
    if response.status_code == 200:
        return True
    else:
        return False
    
def editar_venta(request, id_venta):
    if request.method == 'GET':
        datos_sucursal = obtener_datos_venta(id_venta)

        if datos_sucursal:
            return render(request, 'modificar_ventas.html', context={'venta': datos_sucursal})
        else:
            return render(request, 'LibrosApp/error.html', {'mensaje': 'Error al obtener datos de la sucursal'})

    else:
        nuevos_datos = {
            'id_venta': request.POST.get('id_venta'),
            'id_producto':request.POST.get('id_producto'),
            'id_cliente': request.POST.get('id_cliente'),
            'cantidad' :request.POST.get('cantidad'),
            'subtotal' : request.POST.get('subtotal'),
            'total' :request.POST.get('total'), 
            'impuesto_aplicado' : request.POST.get('impuesto_aplicado'),
            'descuento_aplicado' : request.POST.get('descuento_aplicado'),
            'fecha_venta' : request.POST.get('fecha_venta')
        }
        
        actualizar_datos_venta(nuevos_datos)
        return redirect('ver_ventas')
 
#-----------------------------------------------------------------
def obtener_datos_cliente(identificacion):
    # Hacer una solicitud a la API para obtener los datos de venta
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/cliente/get/{identificacion}'
    response = requests.get(api_url)
    print(api_url)
    
    if response.status_code == 200:
        datos_cliente = response.json()
        return datos_cliente
    else:
        return None

def actualizar_datos_cliente(nuevos_datos2):
    # Hacer una solicitud a la API para actualizar los datos de la venta
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/cliente/editar'
    response = requests.put(api_url,  json={'data': nuevos_datos2}, headers={'Content-Type': 'application/json'})

    print(response)

    print(nuevos_datos2)
    if response.status_code == 200:
        return True
    else:
        return False
    
def editar_cliente(request, identificacion):
    if request.method == 'GET':
        datos_cliente = obtener_datos_cliente(identificacion)

        if datos_cliente:
            return render(request, 'modificar_cliente.html', context={'cliente': datos_cliente})
        else:
            return render(request, 'LibrosApp/error.html', {'mensaje': 'Error al obtener datos del cliente'})

    else:
        nuevos_datos2 = {
            'identificacion': request.POST.get('identificacion'),
            'nombres': request.POST.get('nombres'),
            'apellidos': request.POST.get('apellidos'),
            'direccion': request.POST.get('direccion'),
            'email': request.POST.get('email'),
            'telefono': request.POST.get('telefono'),
            'fecha_nacimiento': request.POST.get('fecha_nacimiento')
        }
        
        # Actualizar los datos de la sucursal
        actualizar_datos_cliente(nuevos_datos2)
        return redirect('ver_cliente')
#---------------------------------------------------------------------
def obtener_datos_producto_sucursales(id_producto):
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/producto/get/{id_producto}'
    response = requests.get(api_url)
    
    if response.status_code == 200:
        datos_producto = response.json()
        return datos_producto
    else:
        return None

def actualizar_datos_producto_sucursales(nuevos_datos):
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/producto/editar'
    response = requests.put(api_url,  json={'data': nuevos_datos}, headers={'Content-Type': 'application/json'})

    print(response)

    print(nuevos_datos)
    if response.status_code == 200:
        return True
    else:
        return False
    
def editar_producto_sucursales(request, id_producto):
    if request.method == 'GET':
        datos_producto = obtener_datos_producto_sucursales(id_producto)

        if datos_producto:  
            return render(request, 'modificar_producto_sucursales.html', context={'producto': datos_producto})
        else:
            return render(request, 'LibrosApp/error.html', {'mensaje': 'Error al obtener datos de la sucursal'})

    else:
        nuevos_datos = {
            'id_producto': request.POST.get('id_producto'),
            'producto': request.POST.get('producto'),
            'cantidad': request.POST.get('cantidad'),
            'unidad_medida': request.POST.get('unidad_medida'),
            'precio_unitario': request.POST.get('precio_unitario'),
            'iva': request.POST.get('iva'),
            'descuento': request.POST.get('descuento'),
            'descripcion': request.POST.get('descripcion')


        }
        
        actualizar_datos_producto_sucursales(nuevos_datos)
        return redirect('ver_producto_sucursales')
#-------------------------------------------------------------------
def eliminar_datos_venta(id_venta):
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/venta/eliminar/{id_venta}'
    response = requests.delete(api_url)

    print(api_url, response.status_code)
    if response.status_code == 200:
        return True
    else:
        return False
     

def eliminar_venta(request, id_venta):    
    eliminar_datos_venta(id_venta)
    return redirect('ver_ventas')
#-----------------------------------------------------------------------------------
def eliminar_datos_cliente(identificacion):
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/cliente/eliminar/{identificacion}'
    response = requests.delete(api_url)

    print(api_url, response.status_code)
    if response.status_code == 200:
        return True
    else:
        return False
     

def eliminar_cliente(request, identificacion):    
    eliminar_datos_cliente(identificacion)
    return redirect('ver_cliente')
#----------------------------------------------------------------------------------------------------       
def eliminar_datos_producto(id_producto):
    api_url = f'http://26.48.208.162:8080/Ventas-Firebase/api/producto/eliminar/{id_producto}'
    response = requests.delete(api_url)

    print(api_url, response.status_code)
    if response.status_code == 200:
        return True
    else:
        return False
     

def eliminar_producto(request, id_producto):    
    eliminar_datos_producto(id_producto)
    return redirect('ver_producto_sucursales')

#--------------------------------------------------------------------------------------------------


def ver_producto_sucursales(request):
    # URL de la API
    api_url = "http://26.48.208.162:8080/Ventas-Firebase/api/producto/listar"

    try:
        response = requests.get(api_url)

        if response.status_code == 200:
            producto_sucursales = response.json()

            return render(request, 'ver_producto_sucursales.html', {'producto_sucursales': producto_sucursales})
        else:
            return render(request, 'error.html', {'mensaje': f"Error al obtener datos de la API. Código de respuesta: {response.status_code}"})

    except requests.RequestException as e:
        # Manejar cualquier excepción que pueda ocurrir durante la solicitud
        return render(request, 'error.html', {'mensaje': f"Error al conectar con la API: {e}"})


#---------------------------------------------------------------------------------------
def ver_cliente(request):
    # URL de la API
    api_url = "http://26.48.208.162:8080/Ventas-Firebase/api/cliente/listar"

    try:
        response = requests.get(api_url)

        if response.status_code == 200:
            clientes = response.json()

            return render(request, 'ver_cliente.html', {'clientes': clientes})
        else:
            return render(request, 'error.html', {'mensaje': f"Error al obtener datos de la API. Código de respuesta: {response.status_code}"})

    except requests.RequestException as e:
        return render(request, 'error.html', {'mensaje': f"Error al conectar con la API: {e}"})

def ver_ventas(request):
    # URL de la API
    api_url = "http://26.48.208.162:8080/Ventas-Firebase/api/venta/listar"

    try:
        response = requests.get(api_url)

        if response.status_code == 200:
            ventas = response.json()

            # Renderizar la plantilla con los datos de la API
            return render(request, 'ver_ventas.html', {'ventas': ventas})
        else:
            # Si la solicitud no fue exitosa, mostrar un mensaje de error
            return render(request, 'error.html', {'mensaje': f"Error al obtener datos de la API. Código de respuesta: {response.status_code}"})

    except requests.RequestException as e:
        # Manejar cualquier excepción que pueda ocurrir durante la solicitud
        return render(request, 'error.html', {'mensaje': f"Error al conectar con la API: {e}"})