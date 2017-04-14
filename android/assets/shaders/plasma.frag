#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
varying float colorValue;
uniform sampler2D u_texture;
uniform mat4 u_projModelView;
uniform float u_time;
uniform float u_strength;
uniform float u_brightness;
uniform float u_centerx;
uniform float u_centery;
uniform float u_scale;
uniform float u_red;
uniform float u_green;
uniform float u_blue;
const float PI = 3.14;

void main() {
float dist;
float colorXY;
float colorX;
float colorY;
vec2 newCoords;

        vec2 c = v_texCoords.xy * u_scale - u_scale/2.0;
        float v = 0.0;
        v += sin((c.x+u_time));
                  v += sin((c.y+u_time)/2.0);
                  v += sin((c.x+c.y+u_time)/2.0);
                  c += u_scale/2.0 * vec2(sin(u_time/u_centerx), cos(u_time/u_centery));
                  v += sin(sqrt(c.x*c.x+c.y*c.y+1.0)+u_time);
                            v = v/2.0;

        float r = sin(u_red * v *10.) /2.0 +.5;
        float g = sin(u_green * v *10.) /2.0 +.5;

        float b = sin(u_blue * v *10.) / 2.0 +.5;
        vec3 colors = vec3(r, g, b);
        gl_FragColor = vec4(colors*u_strength +u_brightness, 1.0);

}