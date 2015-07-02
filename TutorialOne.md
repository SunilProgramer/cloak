# Creating your first cloak project #

**1.** Make sure you have the source code, instructions for obtaining the source can be found at http://code.google.com/p/cloak/source/checkout


**2.** Next you'll need to create a new Eclipse project, for reference here is a picture of my project settings.

![http://cloak.googlecode.com/svn/trunk/docs/images/eclipse_project_settings.png](http://cloak.googlecode.com/svn/trunk/docs/images/eclipse_project_settings.png)

I would suggest following the same
convention during the tutorial stages to avoid any confusion.
If you are unsure how to create a new project follow the official Android instructions at http://developer.android.com/guide/developing/eclipse-adt.html.


**3.** Now we need to link the cloak open source framework into our Eclipse project. Open up your project properties and select "Java Build Path", in the
"Source" tab you will see a button named "Link Source".  You will need to find the location where you checked out the source code in the first
step, for folder name I use cloak.  Once you press OK and refresh your project you see the new cloak folder containing the framework code.

**Note:** This step was performed on a Mac OS X so it may be slightly different on other operating systems.


**4.** Now we have our basic project setup it's time to develop our first application using the framework.  Create a new class called SampleSurface and extend GameSurface.
Your SampleSurface class should look like this

```
package com.cloak

import android.content.Context;

import com.cloak.game.GameSurface;

public class SampleSurface extends GameSurface {

    public Sample(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

}
```

**5.** For our first example we are going to draw a Sprite on the screen.  Create a new Sprite object named mSpriteRobot.
```
Sprite mSpriteRobot;
```

**6.** Next we need to initialize our Sprite and specify its location, we can do all this with the Sprite constructor.  We are going to use 0,0 for the location for this sample.
```
mSpriteRobot = new Sprite(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.android_wrench), 0, 0);
```
Note: Here is the robot image ![http://cloak.googlecode.com/svn/trunk/docs/images/android_wrench.png](http://cloak.googlecode.com/svn/trunk/docs/images/android_wrench.png)

**7.** Open the Activity class we created for the project, we need tell Android to display our SampleSurface class instead of the default main.xml

```
private SampleSurface mSampleSurface;

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
         
    mSampleSurface = new SampleSurface(this);
    setContentView(mSampleSurface);
}
```

**8.** If we run our application at this stage we will be presented with a black screen.  This is because in our SampleSurface doesn't actually do anything
besides create a Sprite object.

![http://cloak.googlecode.com/svn/trunk/docs/images/tut1_blank.png](http://cloak.googlecode.com/svn/trunk/docs/images/tut1_blank.png)

We need to implement a draw method if we want to view our Sprite on the screen.

```
@Override
public void draw() {
    mSpriteRobot.draw(mCanvas);
}
```


**9.** SampleSurface extends GameSurface providing us with access to the framework methods.  draw() is called to render objects and tick() is called to update objects.
I will go into more details about these in the Animated Sprite tutorial. mCanvas is the Canvas reference we draw too.  mCanvas is handled by GameSurface
so you don't need to worry too much about it except for the fact we use mCanvas to draw onto the screen.  We need to pass our Canvas to the Sprite so we can draw to the screen.
After adding these 4 lines of code to SampleSurface run the sample application and you will see an Android Robot drawn holding a wrench.

![http://cloak.googlecode.com/svn/trunk/docs/images/tut1_sprite.png](http://cloak.googlecode.com/svn/trunk/docs/images/tut1_sprite.png)

This wraps up the first tutorial.  Over the coming weeks I will explain the various building blocks required to develop a basic game.  If you have
any suggestions for tutorials, questions or feature requests for the framework please let me know.



**CloakActivity.java**
```
package com.cloak;

import android.app.Activity;
import android.os.Bundle;

public class CloakActivity extends Activity {

    private SampleSurface mSampleSurface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mSampleSurface = new SampleSurface(this);
        setContentView(mSampleSurface);
    }    
    
}
```

**SampleSurface.java**
```
package com.cloak;

import com.cloak.game.GameSurface;
import com.cloak.game.Sprite;

import android.content.Context;
import android.graphics.BitmapFactory;

public class SampleSurface extends GameSurface {

    Sprite mSpriteRobot;
        
    public SampleSurface(Context context) {
        super(context);
        mSpriteRobot = new Sprite(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.android_wrench), 0, 0);
    }

    @Override
    public void draw() {
        mSpriteRobot.draw(mCanvas);
    }
}
```