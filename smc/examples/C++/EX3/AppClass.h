#ifndef _H_APPCLASS
#define _H_APPCLASS

//
// The contents of this file are subject to the Mozilla Public
// License Version 1.1 (the "License"); you may not use this file
// except in compliance with the License. You may obtain a copy of
// the License at http://www.mozilla.org/MPL/
// 
// Software distributed under the License is distributed on an "AS
// IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
// implied. See the License for the specific language governing
// rights and limitations under the License.
// 
// The Original Code is State Machine Compiler (SMC).
// 
// The Initial Developer of the Original Code is Charles W. Rapp.
// Portions created by Charles W. Rapp are
// Copyright (C) 2000 - 2003 Charles W. Rapp.
// All Rights Reserved.
// 
// Contributor(s): 
//
// Name
//	AppClass
//
// Description
//	When a state map executes an action, it is really calling a member function
//	in the context class.
//
// RCS ID
// $Id: AppClass.h,v 1.7 2015/08/02 19:44:34 cwrapp Exp $
//
// CHANGE LOG
// $Log: AppClass.h,v $
// Revision 1.7  2015/08/02 19:44:34  cwrapp
// Release 6.6.0 commit.
//
// Revision 1.6  2014/09/06 19:54:11  fperrad
// remove hard tab
//
// Revision 1.5  2009/12/17 19:51:42  cwrapp
// Testing complete.
//
// Revision 1.4  2005/05/28 13:31:18  cwrapp
// Updated C++ examples.
//
// Revision 1.0  2003/12/14 19:19:26  charlesr
// Initial revision
//

#include "AppClass_sm.h"

#ifdef CRTP
class AppClass : public AppClassContext<AppClass>
#else
class AppClass
#endif
{
private:
#ifndef CRTP
    AppClassContext _fsm;
#endif

    bool isAcceptable;
        // If a string is acceptable, then this variable is set to true;
        // false, otherwise.

public:
    AppClass();
        // Default constructor.

    ~AppClass() {};
        // Destructor.

    bool CheckString(const char*);
        // Checks if the string is acceptable.

    inline void Acceptable()
    { isAcceptable = true; };

    inline void Unacceptable()
    { isAcceptable = false; };
        // State machine actions.

#ifdef SERIALIZE
    int serialize(const std::string& filename);
    int deserialize(const std::string& filename);
#endif
};

#endif
