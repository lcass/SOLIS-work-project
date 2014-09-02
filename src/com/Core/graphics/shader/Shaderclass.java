package com.Core.graphics.shader;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;

import com.Core.Util.FileBuilder;
public class Shaderclass {
	public int programID;
    
    // Vertex Shader ID
    int vertexShaderID;
    // Fragment Shader ID
    int fragmentShaderID;
    
    /**
     * Create a new ShaderProgram.
     */
    public Shaderclass()
    {
        programID = glCreateProgram();
    }
    
    /**
     * Attach a Vertex Shader to this program.
     * 
     * @param name The file name of the vertex shader.
     */
    public void attachVertexShader(String name)
    {
        // Load the source
        String vertexShaderSource = FileBuilder.gettext(name);
        
        // Create the shader and set the source
        vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShaderID, vertexShaderSource);
        
        // Compile the shader
        glCompileShader(vertexShaderID);
        
        // Check for errors
        if (glGetShaderi(vertexShaderID, GL_COMPILE_STATUS) == GL_FALSE)
        {
            System.err.println("Unable to create vertex shader:");
            dispose();
           
        }
        
        // Attach the shader
        glAttachShader(programID, vertexShaderID);
    }
    
    /**
     * Attach a Fragment Shader to this program.
     * 
     * @param name The file name of the Fragment Shader.
     */
    public void attachFragmentShader(String name)
    {
        // Read the source
        String fragmentShaderSource = FileBuilder.gettext(name);

        // Create the shader and set the source
        fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderID, fragmentShaderSource);

        // Compile the shader
        glCompileShader(fragmentShaderID);
        
        // Check for errors
        if (glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS) == GL_FALSE)
        {
            System.err.println("Unable to create fragment shader:");
            dispose();

        }

        // Attach the shader
        glAttachShader(programID, fragmentShaderID);
    }
    
    /**
     * Links this program in order to use.
     */
    public void link()
    {
        // Link this program
        glLinkProgram(programID);
        
        // Check for linking errors
        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE)
        {
            System.err.println("Unable to link shader program:");
            dispose();
           
        }
    }
    
    /**
     * Bind this program to use.
     */
    public void bind()
    {
        glUseProgram(programID);
    }
    
    /**
     * Unbind the shader program.
     */
    public static void unbind()
    {
        glUseProgram(0);
    }
    
    /**
     * Dispose the program and shaders.
     */
    public void dispose()
    {
        // Unbind the program
        unbind();
        
        // Detach the shaders
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        
        // Delete the shaders
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        
        // Delete the program
        glDeleteProgram(programID);
    }
}
