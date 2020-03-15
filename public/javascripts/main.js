window.onload = () =>{
  var btn = document.getElementById('btn');
  var modal = document.getElementById('modal');

  btn.addEventListener('click', function() {
    modal.style.display = 'block';
  });

  window.addEventListener('click', function(e) {
    if (e.target == modal) {
      modal.style.display = 'none';
    }
  });
}