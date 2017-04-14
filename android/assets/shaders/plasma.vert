attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_projModelView;
uniform mat4 u_projTrans;
uniform mat4 u_worldTrans;
uniform float u_time;
uniform float u_xmodifier;
uniform float u_ymodifier;
uniform float u_centerx_modifier;
uniform float u_centery_modifier;
uniform float u_scale;
varying vec2 v_texCoords;



void main() {
float dist;
float colorX;float colorY;
float colorXY;
vec4 inPosition;
    #ifdef IsThreeD
        inPosition = u_projTrans * u_worldTrans * a_position;
     #else
        inPosition = u_projModelView * a_position;
      #endif

    v_texCoords = a_texCoord0;

    gl_Position = inPosition;
}