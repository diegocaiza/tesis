
function eliminar() {
    if(confirm('Est\u00e1 seguro de eliminar el registro?'))
        return true;
    return false;
}

function modificar() {
    if(confirm('Est\u00e1 seguro que los datos ingresados son correctos?'))
        return true;
    return false;
}

function bloquearp() {
    if(confirm('Est\u00e1 seguro de deshabilitar a \u00e9ste profesor? Tome en cuenta que no estar\u00e1 disponible en el sistema'))
        return true;
    return false;
}

function desbloquearp() {
    if(confirm('Est\u00e1 seguro de habilitar a este profesor?'))
        return true;
    return false;
}
function bloqueara() {
    if(confirm('Est\u00e1 seguro de deshabilitar a \u00e9ste alumno? Tome en cuenta que no estar\u00e1 disponible en el sistema'))
        return true;
    return false;
}

function desbloqueara() {
    if(confirm('Est\u00e1 seguro de habilitar a este alumno?'))
        return true;
    return false;
}
function enviar_mail() {
    if(confirm('Est\u00e1 seguro de enviar el mensaje de correo electrónico?'))
        return true;
    return false;
}

function enviar_mensaje() {
    if(confirm('Est\u00e1 seguro de enviar el mensaje?'))
        return true;
    return false;
}

function res_clave() {
    if(confirm('Est\u00e1 seguro de resetear la clave del usuario?'))
        return true;
    return false;
}
function activar_programa() {
    if(confirm('Est\u00e1 seguro de activar el Programa Postgrado?'))
        return true;
    return false;
}
function desactivar_programa() {
    if(confirm('Est\u00e1 seguro de desactivar el  Programa Postgrado?'))
        return true;
    return false;
}