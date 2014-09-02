attribute vec2 position;
attribute vec2 vectex;
uniform vec2 transform;
uniform float scalar;
varying vec2 vecdata;
void main()
{
	vec4 offset = vec4(position.x+transform.x,(position.y+transform.y),1,1);
	gl_Position = offset;
	vecdata = vectex;
}
