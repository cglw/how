/**
 * Copyright (C) 2017 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */






var RE = {};




RE.currentSelection = {
    "startContainer": 0,
    "startOffset": 0,
    "endContainer": 0,
    "endOffset": 0};
var current_bold=false;
RE.editor = document.getElementById('editor');

document.addEventListener("selectionchange", function() { RE.backuprange(); });

// Initializations
RE.callback = function() {
    window.location.href = "re-callback://" + encodeURI(RE.getHtml());
}




RE.setHtml = function(contents) {
    RE.editor.innerHTML = decodeURIComponent(contents.replace(/\+/g, '%20'));
}

RE.getHtml = function() {
    return RE.editor.innerHTML;
}

RE.getText = function() {
    return RE.editor.innerText;
}

RE.setBaseTextColor = function(color) {
    RE.editor.style.color  = color;
}

RE.setBaseFontSize = function(size) {
    RE.editor.style.fontSize = size;
}

RE.setPadding = function(left, top, right, bottom) {
  RE.editor.style.paddingLeft = left;
  RE.editor.style.paddingTop = top;
  RE.editor.style.paddingRight = right;
  RE.editor.style.paddingBottom = bottom;
}

RE.setBackgroundColor = function(color) {
    document.body.style.backgroundColor = color;
}

RE.setBackgroundImage = function(image) {
    RE.editor.style.backgroundImage = image;
}

RE.setWidth = function(size) {
    RE.editor.style.minWidth = size;
}

RE.setHeight = function(size) {
    RE.editor.style.height = size;
}

RE.setTextAlign = function(align) {
    RE.editor.style.textAlign = align;
}

RE.setVerticalAlign = function(align) {
    RE.editor.style.verticalAlign = align;
}

RE.setPlaceholder = function(placeholder) {
    RE.editor.setAttribute("placeholder", placeholder);
}

RE.setInputEnabled = function(inputEnabled) {
    RE.editor.contentEditable = String(inputEnabled);
}

RE.undo = function() {
    document.execCommand('undo', false, null);
}

RE.redo = function() {
    document.execCommand('redo', false, null);
}

RE.setBold = function(bold) {
    console.info(document.queryCommandState('bold')+'--->'+bold)
    console.info(typeof document.queryCommandState('bold'))
    console.info(typeof bold)
    if(String(document.queryCommandState('bold'))!==bold)
    {
         document.execCommand('bold', false, null);
         current_bold=document.queryCommandState('bold');
         console.info(document.queryCommandState('bold')+"--->do_complete")

    }
 }

RE.setItalic = function() {
    document.execCommand('italic', false, null);
}

RE.setSubscript = function() {
    document.execCommand('subscript', false, null);
}

RE.setSuperscript = function() {
    document.execCommand('superscript', false, null);
}

RE.setStrikeThrough = function() {
    document.execCommand('strikeThrough', false, null);
}

RE.setUnderline = function() {
    document.execCommand('underline', false, null);
}

RE.setBullets = function() {
    document.execCommand('insertUnorderedList', false, null);
}

RE.setNumbers = function() {
    document.execCommand('insertOrderedList', false, null);
}

RE.setTextColor = function(color) {
    RE.restorerange();
    document.execCommand("styleWithCSS", null, true);
    document.execCommand('foreColor', false, color);
    document.execCommand("styleWithCSS", null, false);
}

RE.setTextBackgroundColor = function(color) {
    RE.restorerange();
    document.execCommand("styleWithCSS", null, true);
    document.execCommand('hiliteColor', false, color);
    document.execCommand("styleWithCSS", null, false);
}

RE.setFontSize = function(fontSize){
    document.execCommand("fontSize", false, fontSize);
}

RE.setHeading = function(heading) {
    document.execCommand('formatBlock', false, '<h'+heading+'>');
}

RE.setIndent = function() {
    document.execCommand('indent', false, null);
}

RE.setOutdent = function() {
    document.execCommand('outdent', false, null);
}

RE.setJustifyLeft = function() {
    document.execCommand('justifyLeft', false, null);
}

RE.setJustifyCenter = function() {
    document.execCommand('justifyCenter', false, null);
}

RE.setJustifyRight = function() {
    document.execCommand('justifyRight', false, null);
}

RE.setBlockquote = function() {
    document.execCommand('formatBlock', false, '<blockquote>');
}


RE.insertHTML = function(html) {
    RE.restorerange();
    document.execCommand('insertHTML', false, html);
}
RE.insertHr = function() {
    var html = '<hr/>';
    RE.insertHTML(html);
}
RE.insertBr = function() {
    var html = '<br/>';
    RE.insertHTML(html);
}

RE.insertLink = function(url, title) {
    RE.restorerange();
    var sel = document.getSelection();
    if (sel.toString().length == 0) {
        document.execCommand("insertHTML",false,"<a href='"+url+"'>"+title+"</a>");
    } else if (sel.rangeCount) {
       var el = document.createElement("a");
       el.setAttribute("href", url);
       el.setAttribute("title", title);

       var range = sel.getRangeAt(0).cloneRange();
       range.surroundContents(el);
       sel.removeAllRanges();
       sel.addRange(range);
   }
    RE.callback();
}

RE.setTodo = function(text) {
    var html = '<input type="checkbox" name="'+ text +'" value="'+ text +'"/> &nbsp;';
    document.execCommand('insertHTML', false, html);
}

RE.prepareInsert = function() {
    RE.backuprange();
}

RE.backuprange = function(){
    var selection = window.getSelection();
    if (selection.rangeCount > 0) {
      var range = selection.getRangeAt(0);
      RE.currentSelection = {
          "startContainer": range.startContainer,
          "startOffset": range.startOffset,
          "endContainer": range.endContainer,
          "endOffset": range.endOffset};
    }
}

RE.restorerange = function(){
    var selection = window.getSelection();
    selection.removeAllRanges();
    var range = document.createRange();
    range.setStart(RE.currentSelection.startContainer, RE.currentSelection.startOffset);
    range.setEnd(RE.currentSelection.endContainer, RE.currentSelection.endOffset);
    selection.addRange(range);
}

RE.enabledEditingItems = function(e) {
    var items = [];
    console.info('enabledEditingItems-->'+document.queryCommandState('bold'))
    console.info('enabledEditingItems--current_bold>'+current_bold)

    if(document.queryCommandState('bold')!==current_bold)
    {
      document.execCommand('bold', false, null);
    }
    if (current_bold) {
        items.push('bold');
    }
    if (document.queryCommandState('italic')) {
        items.push('italic');
    }
    if (document.queryCommandState('subscript')) {
        items.push('subscript');
    }
    if (document.queryCommandState('superscript')) {
        items.push('superscript');
    }
    if (document.queryCommandState('strikeThrough')) {
        items.push('strikeThrough');
    }
    if (document.queryCommandState('underline')) {
        items.push('underline');
    }
    if (document.queryCommandState('insertOrderedList')) {
        items.push('orderedList');
    }
    if (document.queryCommandState('insertUnorderedList')) {
        items.push('unorderedList');
    }
    if (document.queryCommandState('justifyCenter')) {
        items.push('justifyCenter');
    }
    if (document.queryCommandState('justifyFull')) {
        items.push('justifyFull');
    }
    if (document.queryCommandState('justifyLeft')) {
        items.push('justifyLeft');
    }
    if (document.queryCommandState('justifyRight')) {
        items.push('justifyRight');
    }
    if (document.queryCommandState('insertHorizontalRule')) {
        items.push('horizontalRule');
    }
    var formatBlock = document.queryCommandValue('formatBlock');
    if (formatBlock.length > 0) {
        items.push(formatBlock);
    }

    window.location.href = "re-state://" + encodeURI(items.join(','));
}

RE.focus = function() {
    var range = document.createRange();
    range.selectNodeContents(RE.editor);
    range.collapse(false);
    var selection = window.getSelection();
    selection.removeAllRanges();
    selection.addRange(range);
    RE.editor.focus();
}

RE.blurFocus = function() {
    RE.editor.blur();
}

RE.removeFormat = function() {
    document.execCommand('removeFormat', false, null);
}

// Event Listeners
RE.editor.addEventListener("input", RE.callback);
RE.editor.addEventListener("keyup", function(e) {
    var KEY_LEFT = 37, KEY_RIGHT = 39;
    if (e.which == KEY_LEFT || e.which == KEY_RIGHT) {
        console.info('keyup--->'+e.which)
        RE.enabledEditingItems(e);
    }
});
RE.editor.addEventListener("click",function(e){
    console.info('click--->'+e.which);
    RE.enabledEditingItems();

});




RE.scrollTo=function(where)
{
            var me = this;
            me.site = [];
            me.sleep = me.sleep ? me.sleep : 16;
            me.fx = me.fx ? me.fx : 6;
            clearInterval (me.interval);
            var dh = document.documentElement.scrollHeight || document.body.scrollHeight;
            var height = !!where ? dh : 0;
            me.interval = setInterval (function ()
            {
                var top = document.documentElement.scrollTop || document.body.scrollTop;
                var speed = (height - top) / me.fx;
                if (speed === me.site[0])
                {
                    window.scrollTo (0, height);
                    clearInterval (me.interval);
                }
                window.scrollBy (0, speed);
                me.site.unshift (speed);
            }, me.sleep);
}

RE.scrollToAndInsert = function() {
   RE.insertHTML('<br/><br/>');
   RE.scrollTo(1)

}
RE.createImage = function() {
   var img=document.createElement("img");
   document.getElementById('editor').appendChild(img);

   return img;

}


RE.insertImage = function(url, alt) {
    var html = '<img  alt="' + alt + '"  id="test"/>';
    var img=RE.createImage()
    img.onload =function () {
        RE.scrollToAndInsert()
    }
    img.src=url
    img.alt=alt
}


RE.test=function()
{



 var WVJBIframe = document.createElement('iframe');
 WVJBIframe.style.display = 'block';
//    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
// WVJBIframe.src = 'www.baidu.com';
 WVJBIframe.src = 'style.css';
 WVJBIframe.style.color = 'red';

   //WVJBIframe.src = 'https://__BRIDGE_LOADED__';
   //WVJBIframe.src = 'https://__BRIDGE_LOADED__';
   document.getElementById('editor').appendChild(WVJBIframe);
 setTimeout(
     function() {
       document.getElementById('editor').removeChild(WVJBIframe);
        },
   20000);
 console.info('WVJBIframe')


}



