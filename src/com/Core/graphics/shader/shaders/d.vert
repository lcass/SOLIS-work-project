uniform vec2 transform;

void main()
{
	gl_Position =gl_Vertex+vec4(transform,0,0);
}