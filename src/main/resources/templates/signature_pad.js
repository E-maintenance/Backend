function resizeCanvas() {
    var oldContent = signaturePad.toData();
    var ratio = Math.max(window.devicePixelRatio || 1, 1);
    canvas.width = canvas.offsetWidth * ratio;
    canvas.height = canvas.offsetHeight * ratio;
    canvas.getContext("2d").scale(ratio, ratio);
    signaturePad.clear();
    signaturePad.fromData(oldContent);

}
window.onresize = resizeCanvas;
resizeCanvas();
function submitForm() {
    //Unterschrift in verstecktes Feld übernehmen
    document.getElementById('signature').value = signaturePad.toDataURL();
}