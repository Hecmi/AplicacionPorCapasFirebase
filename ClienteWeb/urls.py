"""
URL configuration for ClienteWeb project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
# NombreDelProyecto/urls.py
from django.contrib import admin
from django.urls import include, path

urlpatterns = [
    path('admin/', admin.site.urls),
    path('LibrosApp/', include('LibrosApp.urls')),
    # Agrega una ruta para la vista principal (puede ser la misma que agregar_libro o cualquier otra)
    path('', include('LibrosApp.urls')),  # Esto asume que tienes una ruta vacía en LibrosApp/urls.py
]

