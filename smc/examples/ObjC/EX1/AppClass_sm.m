/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : AppClass.sm
 */


//
// The contents of this file are subject to the Mozilla Public
// License Version 1.1 (the "License"); you may not use this file
// except in compliance with the License. You may obtain a copy
// of the License at http://www.mozilla.org/MPL/
// 
// Software distributed under the License is distributed on an
// "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
// implied. See the License for the specific language governing
// rights and limitations under the License.
// 
// The Original Code is State Machine Compiler (SMC).
// 
// The Initial Developer of the Original Code is Charles W. Rapp.
// Portions created by Charles W. Rapp are
// Copyright (C) 2007. Charles W. Rapp.
// All Rights Reserved.
//
// Contributor(s): 
//     Chris Liscio
//
// State Map
//	This state map is recognizes the regular expression 0*1*.
//
// RCS ID
// $Id: AppClass.sm,v 1.1 2007/01/15 00:23:48 cwrapp Exp $
//
// CHANGE LOG
// $Log: AppClass.sm,v $
// Revision 1.1  2007/01/15 00:23:48  cwrapp
// Release 4.4.0 initial commit.
//


#import "AppClass.h"
#import "AppClass_sm.h"
// Class declarations.
@implementation Map1
    static Map1_Start *gMap1_Start = nil;
    static Map1_Zeros *gMap1_Zeros = nil;
    static Map1_Ones *gMap1_Ones = nil;
    static Map1_OK *gMap1_OK = nil;
    static Map1_Error *gMap1_Error = nil;

+ (Map1_Start*)Start;
{
    if (!gMap1_Start)
    {
        gMap1_Start = [[Map1_Start alloc] initWithName:@"Map1::Start" stateId:0];
    }
    return gMap1_Start;
}

+ (Map1_Zeros*)Zeros;
{
    if (!gMap1_Zeros)
    {
        gMap1_Zeros = [[Map1_Zeros alloc] initWithName:@"Map1::Zeros" stateId:0];
    }
    return gMap1_Zeros;
}

+ (Map1_Ones*)Ones;
{
    if (!gMap1_Ones)
    {
        gMap1_Ones = [[Map1_Ones alloc] initWithName:@"Map1::Ones" stateId:0];
    }
    return gMap1_Ones;
}

+ (Map1_OK*)OK;
{
    if (!gMap1_OK)
    {
        gMap1_OK = [[Map1_OK alloc] initWithName:@"Map1::OK" stateId:0];
    }
    return gMap1_OK;
}

+ (Map1_Error*)Error;
{
    if (!gMap1_Error)
    {
        gMap1_Error = [[Map1_Error alloc] initWithName:@"Map1::Error" stateId:0];
    }
    return gMap1_Error;
}

+ (void) cleanupStates
{
    [gMap1_Start S_RELEASE]; gMap1_Start = nil;
    [gMap1_Zeros S_RELEASE]; gMap1_Zeros = nil;
    [gMap1_Ones S_RELEASE]; gMap1_Ones = nil;
    [gMap1_OK S_RELEASE]; gMap1_OK = nil;
    [gMap1_Error S_RELEASE]; gMap1_Error = nil;
}
@end

@implementation AppClassState
- (void)Entry:(AppClassContext*)context
{
}
- (void)Exit:(AppClassContext*)context
{
}
- (void)EOS:(AppClassContext*)context;
{
    [self Default:context];
}
- (void)One:(AppClassContext*)context;
{
    [self Default:context];
}
- (void)Unknown:(AppClassContext*)context;
{
    [self Default:context];
}
- (void)Zero:(AppClassContext*)context;
{
    [self Default:context];
}

- (void)Default:(AppClassContext*)context;
{
    if ( [context debugFlag] )
{        TRACE(@"TRANSITION   : Default\n\r");
    }
    NSAssert( NO, @"Default transition" );
}
@end


@implementation Map1_Default
@end
@implementation Map1_Start

- (void)EOS:(AppClassContext*)context;
{
    id<AppClass> ctxt = [context owner];
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Start\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 EOS()\n\r");
    }
    [context clearState];
    [ctxt Acceptable];
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 EOS()\n\r");
    }
    [context setState:[Map1 OK]];
    [[context state] Entry:context];
}

- (void)One:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Start\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 One()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 One()\n\r");
    }
    [context setState:[Map1 Ones]];
    [[context state] Entry:context];
}

- (void)Unknown:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Start\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 Unknown()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 Unknown()\n\r");
    }
    [context setState:[Map1 Error]];
    [[context state] Entry:context];
}

- (void)Zero:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Start\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 Zero()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 Zero()\n\r");
    }
    [context setState:[Map1 Zeros]];
    [[context state] Entry:context];
}
@end

@implementation Map1_Zeros

- (void)EOS:(AppClassContext*)context;
{
    id<AppClass> ctxt = [context owner];
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Zeros\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 EOS()\n\r");
    }
    [context clearState];
    [ctxt Acceptable];
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 EOS()\n\r");
    }
    [context setState:[Map1 OK]];
    [[context state] Entry:context];
}

- (void)One:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Zeros\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 One()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 One()\n\r");
    }
    [context setState:[Map1 Ones]];
    [[context state] Entry:context];
}

- (void)Unknown:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Zeros\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 Unknown()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 Unknown()\n\r");
    }
    [context setState:[Map1 Error]];
    [[context state] Entry:context];
}

- (void)Zero:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Zeros\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 Zero()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 Zero()\n\r");
    }
    [context setState:[Map1 Zeros]];
    [[context state] Entry:context];
}
@end

@implementation Map1_Ones

- (void)EOS:(AppClassContext*)context;
{
    id<AppClass> ctxt = [context owner];
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Ones\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 EOS()\n\r");
    }
    [context clearState];
    [ctxt Acceptable];
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 EOS()\n\r");
    }
    [context setState:[Map1 OK]];
    [[context state] Entry:context];
}

- (void)One:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Ones\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 One()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 One()\n\r");
    }
    [context setState:[Map1 Ones]];
    [[context state] Entry:context];
}

- (void)Unknown:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Ones\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 Unknown()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 Unknown()\n\r");
    }
    [context setState:[Map1 Error]];
    [[context state] Entry:context];
}

- (void)Zero:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Ones\n\r");
    }
    [[context state] Exit:context];
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 Zero()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 Zero()\n\r");
    }
    [context setState:[Map1 Error]];
    [[context state] Entry:context];
}
@end

@implementation Map1_OK
@end

@implementation Map1_Error

- (void)EOS:(AppClassContext*)context;
{
    id<AppClass> ctxt = [context owner];
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Error\n\r");
    }
    AppClassState* EndStateName = [context state];

    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 EOS()\n\r");
    }
    [context clearState];
    [ctxt Unacceptable];
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 EOS()\n\r");
    }
    [context setState:EndStateName];
}

- (void)One:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Error\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 One()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 One()\n\r");
    }
}

- (void)Unknown:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Error\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 Unknown()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 Unknown()\n\r");
    }
}

- (void)Zero:(AppClassContext*)context;
{
    if ( [context debugFlag] )
    {
        TRACE(@"LEAVING STATE   : Map1::Error\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"ENTER TRANSITION: Map1 Zero()\n\r");
    }
    if ( [context debugFlag] )
    {
        TRACE(@"EXIT TRANSITION : Map1 Zero()\n\r");
    }
}
@end

@implementation AppClassContext
- (id)initWithOwner:(id<AppClass>)owner;
{
    self = [super initWithState:[Map1 Start]];
    if (!self)
{
        return nil;
    }
    _owner = owner;
    return self;
}
- (id)initWithOwner:(id<AppClass>)owner state:(SMCState*)aState;
{
    self = [super initWithState: aState];
    if (!self)
{
        return nil;
    }
    _owner = owner;
    return self;
}
- (void)dealloc
{
    [Map1 cleanupStates];
    [super S_DEALLOC];
}
- (AppClassState*)state;
{
    return (AppClassState*)_state;
}
- (id<AppClass>)owner;
{
    return _owner;
}
- (void)enterStartState;
{
    [[self state] Entry:self];
}

- (void)EOS;
{
    [self setTransition:@"EOS"];
    [[self state] EOS:self];
    [self setTransition:nil];
}

- (void)One;
{
    [self setTransition:@"One"];
    [[self state] One:self];
    [self setTransition:nil];
}

- (void)Unknown;
{
    [self setTransition:@"Unknown"];
    [[self state] Unknown:self];
    [self setTransition:nil];
}

- (void)Zero;
{
    [self setTransition:@"Zero"];
    [[self state] Zero:self];
    [self setTransition:nil];
}
@end

/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */