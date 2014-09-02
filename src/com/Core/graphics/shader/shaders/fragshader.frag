
uniform sampler2D tex;
varying vec2 vecdata;
void main(){
	gl_FragColor = texture2D(tex,vecdata);
}