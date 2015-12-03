var rippleIn = function(e){
    var target = e.target;
    if (target.tagName.toLowerCase() !== 'a') return false;
    var rect = target.getBoundingClientRect();
    var ripple = target.querySelector('.ripple');
    if (!ripple) {
    var ripple = document.createElement('span');
        ripple.style.height = ripple.style.width = Math.max(rect.width, rect.height) + 'px';
        target.appendChild(ripple);
    }
    console.log("ripple in");
    ripple.className = 'ripple';
    var top = e.pageY - rect.top - ripple.offsetHeight / 2 - document.body.scrollTop;
    var left = e.pageX - rect.left - ripple.offsetWidth / 2 - document.body.scrollLeft;
    ripple.style.top = top + 'px';
    ripple.style.left = left + 'px';
    ripple.classList.add('in');
    return false;
};

var rippleOut = function(e){
    var target = e.target;
    var ripple = target.querySelector('.ripple');
    if(ripple){
        ripple.classList.add('out');
        setTimeout(function(){
            ripple.parentNode.removeChild(ripple);
        }, 500);
    }
    return false;
};
document.addEventListener('mousedown', rippleIn, false);
document.addEventListener('mouseup', rippleOut, false);