# TiDialogs for Android

A module with the missing native Android dialogs.

### Multi-Choice Dialog

![multi](http://developer.android.com/images/ui/dialog_checkboxes.png)

### Date and Time Pickers

![pickers](http://developer.android.com/images/ui/pickers.png)

## Install

The modules/zip files is in the the `dist` folder of the repository. Add it to your project like you would any other native module.

Require the modules with the following code:

~~~
var Dialogs = require("yy.tidialogs");
~~~


## Usage

### Multi-Choice Dialog

Here is an example usage:

~~~
// Create the dialog

var picker = Dialogs.createMultiPicker({
  title:"Hello World", 
  options:["A","B","C"], 
  selected: ["B","C"] // <-- optional
});

// Add the click listener
picker.addEventListener('click',function(e){
  var indexes    = e.indexes;    // selected indexes
  var selections = e.selections; // the actual selected options.
});

// open it
picker.show();
~~~

### Date Picker

Here is an example usage:

~~~
// Create the dialog

// value property is priority
var picker = Dialogs.createDatePicker({
  okButtonTitle: 'Set',         // <-- optional, default "Done"
  cancelButtonTitle: 'Cancel',  // <-- optional, default "Cancel"
  value: new Date(),            // <-- optional
  day: 28,                      // <-- optional
  month: 7,                     // <-- optional - java/javascript month, i.e. August
  year: 1975                    // <-- optional
});

// Add the click listener
picker.addEventListener('click',function(e){
  if (!e.cancel) {
    var value = e.value; // JavaScript Date object
    var day   = e.day;
    var month = e.month; // Jan === 0
    var year  = e.year;
  }
});

// open it
picker.show();
~~~

### Time Picker

Here is an example usage:

~~~
// Create the dialog

// value property is priority
var picker = Dialogs.createTimePicker({
  okButtonTitle: 'Set',         // <-- optional, default "Done"
  cancelButtonTitle: 'Cancel',  // <-- optional, default "Cancel"
  value: new Date(),            // <-- optional, JavaScript Date object
  hour: 10,                     // <-- optional
  minute: 30                    // <-- optional
});

// Add the click listener
picker.addEventListener('click',function(e){
  if (!e.cancel) {
    var value = e.value; // JavaScript Date object
    var hour   = e.hour;
    var minute = e.minute; 
  }
});

// open it
picker.show();
~~~


## Changelog
* Added cancel button are displayed, Honeycomb later.
* Added okButtonTitle, cancelButtonTitle and value properties.
* Compatible Ti.UI.Picker.showTimePickerDialog and Ti.UI.Picker.showDatePickerDialog properties.


###Licence: MIT
